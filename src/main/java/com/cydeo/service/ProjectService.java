package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;

public interface ProjectService extends CrudService<ProjectDTO,String>{

    void completeProject(ProjectDTO project);
}
