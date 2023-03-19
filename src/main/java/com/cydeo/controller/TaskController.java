package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;

    public TaskController(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createTask(Model model){
        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());
        return "task/create";
    }

    @PostMapping("/create")
    public String insertTask(TaskDTO task){
        taskService.save(task);
        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteById(id);
        return "redirect:/task/create";
    }

    @GetMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, Model model){
        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());
        return "/task/update";
    }

//    @PostMapping("/update/{id}")
//    public String editTask(@PathVariable Long id , @ModelAttribute TaskDTO task){
//        task.setId(id);
//        taskService.update(task);
//        return "redirect:/task/create";
//    }

    @PostMapping("/update/{id}")
    public String editTask(TaskDTO task){
        taskService.update(task);
        return "redirect:/task/create";
    }

    @GetMapping("/employee/pending-tasks")
    public String pendingTasks(){
        return "/task/pending-tasks";
    }

}
