package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO project) {
        if(project.getProjectStatus()==null){
            project.setProjectStatus(Status.OPEN);
        }
        return super.save(project.getProjectCode(),project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO project) {

        if(project.getProjectStatus()==null){
            project.setProjectStatus(findById(project.getProjectCode()).getProjectStatus());
        }

        super.update(project.getProjectCode(), project);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void completeProject(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETE);
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manger) {

        return findAll().stream()
                .filter(p -> p.getAssignedManager().getUserName().equals(manger.getUserName()))
                .map(project -> {

                    List<TaskDTO> tasksList = taskService.findTasksByManager(project.getAssignedManager());

                    int completeTaskCounts = (int)tasksList.stream()
                            .filter(p -> p.getProject().equals(project) && p.getTaskStatus() == Status.COMPLETE).count();
                    int unfinishedTaskCounts = (int)tasksList.stream()
                            .filter(p -> p.getProject().equals(project) && p.getTaskStatus() != Status.COMPLETE).count();

                    project.setCompleteTaskCounts(completeTaskCounts);
                    project.setUnfinishedTaskCounts(unfinishedTaskCounts);

                    return project;

                })
                .collect(Collectors.toList());

    }
}
