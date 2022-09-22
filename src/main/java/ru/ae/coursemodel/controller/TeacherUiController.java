package ru.ae.coursemodel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.ae.coursemodel.dto.teacher.TeacherCreateDto;
import ru.ae.coursemodel.service.TeacherService;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherUiController {

    private final TeacherService teacherService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "teacher/teachers";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return teacherService.findById(id)
                .map(teacher -> {
                    model.addAttribute("teacher", teacher);
                    return "teacher/teacher";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String saveTeacher(Model model, @ModelAttribute("teacher") TeacherCreateDto teacher) {
        model.addAttribute("teacher", teacher);
        return "teacher/teacher-create";
    }

    @PostMapping
    public String create(@ModelAttribute TeacherCreateDto teacherCreateDto) {
        return "redirect:/teachers/" + teacherService.createTeacher(teacherCreateDto).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute TeacherCreateDto teacherCreateDto) {
        return teacherService.updateTeacher(id, teacherCreateDto)
                .map(it -> "redirect:/teachers/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        if(!teacherService.deleteTeacher(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/teachers";
    }
}
