package employeemanagement.controller;

import employeemanagement.model.Employee;
import employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmployeeControler {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String goHome(Model model){
        List<Employee> listEmployees = employeeService.getAllEmployees();
        model.addAttribute("listEmployees",listEmployees);
        return "index";
    }

    @GetMapping("showNewEmployeeForm")
    public String showNewEmployeeFomr(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "newEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }
}
