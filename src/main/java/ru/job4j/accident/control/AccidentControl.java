package ru.job4j.accident.control;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 12.02.2021
 * email roman9628@gmail.com
 */
@Controller
public class AccidentControl {

    private final AccidentMem accidents;
    private static final Logger LOG = LoggerFactory.getLogger(AccidentControl.class.getName());
    private static final Marker MARKER = MarkerFactory.getMarker("Controller");

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int typeId) {
        LOG.info(MARKER, "AccidentControl save accident {}", accident);
        accidents.addToStore(Accident.of(
                accident.getId(),
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                this.accidents.getAccidentTypeById(typeId)));
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.getAccidentById(id));
        return "accident/update";
    }

    @ModelAttribute
    public void addAttributeForCreateAndUpdateRequest(Model model) {
        List<AccidentType> types = new ArrayList<>();
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        model.addAttribute("types", types);
    }
}