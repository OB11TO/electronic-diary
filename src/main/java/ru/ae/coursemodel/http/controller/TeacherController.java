package ru.ae.coursemodel.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ae.coursemodel.dto.PageResponse;
import ru.ae.coursemodel.dto.teacher.TeacherCreateDto;
import ru.ae.coursemodel.dto.teacher.TeacherFilter;
import ru.ae.coursemodel.service.TeacherService;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public String findAll(Model model, TeacherFilter filter, Pageable pageable) {
        var page = teacherService.findAll(filter, pageable);
        model.addAttribute("teachers", PageResponse.of(page));
        model.addAttribute("filter", filter);
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
    public String create(@ModelAttribute @Validated TeacherCreateDto teacher,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("teacher", teacher);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/teachers/create";
        }
        return "redirect:/teachers/" + teacherService.createTeacher(teacher).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute @Validated TeacherCreateDto teacherCreateDto) {
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
