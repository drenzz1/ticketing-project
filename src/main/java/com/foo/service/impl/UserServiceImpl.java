package com.foo.service.impl;

import com.foo.dto.ProjectDTO;
import com.foo.dto.TaskDTO;
import com.foo.dto.UserDTO;
import com.foo.entity.User;
import com.foo.mapper.UserMapper;
import com.foo.repository.UserRepository;
import com.foo.service.ProjectService;
import com.foo.service.TaskService;
import com.foo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProjectService projectService;
    private final TaskService taskService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, @Lazy ProjectService projectService, TaskService taskService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public List<UserDTO> listAllUsers() {
       return  userRepository.findAll().stream().map(userMapper::convertToDto).toList();
    }

    @Override
    public UserDTO findByUsername(String username) {
      return userMapper.convertToDto(userRepository.findByUserName(username));
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(userMapper.convertToEntity(userDTO));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = userRepository.findByUserName(userDTO.getUserName());
        User convertedUser = userMapper.convertToEntity(userDTO);
        convertedUser.setId(user.getId());
        userRepository.save(convertedUser);
        return findByUsername(userDTO.getUserName());
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUserName(username);
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUserName(username);
        if (checkIfUserCanBeDeleted(user)){
            user.setIsDeleted(true);
            user.setUserName(user.getUserName() + " -" + user.getId());
            userRepository.save(user);
        }
    }


    private boolean checkIfUserCanBeDeleted(User user ){
        switch (user.getRole().getDescription()){
            case "Manager":
                List<ProjectDTO>projectDTOList=projectService.readAllByAssignedManager(user);
                return projectDTOList.size()==0;
            case "Employee":
                List<TaskDTO>taskDTOList=taskService.readAllByAssignedEmployee(user);
                return taskDTOList.size()==0;
            default:
                return true;
        }
    }
    @Override
    public List<UserDTO> listAllByRole(String role) {
        return userRepository.findByRoleDescriptionIgnoreCase(role).stream().map(userMapper::convertToDto).toList();
    }
}
