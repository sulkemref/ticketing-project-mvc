package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {
    @Override
    public ProjectDTO save(ProjectDTO project) {
        if(project.getPtojectStatus()==null){
            project.setPtojectStatus(Status.OPEN);
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

        if(project.getPtojectStatus()==null){
            project.setPtojectStatus(findById(project.getProjectCode()).getPtojectStatus());
        }

        super.update(project.getProjectCode(), project);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void completeProject(ProjectDTO project) {
        project.setPtojectStatus(Status.COMPLETE);
    }
}
