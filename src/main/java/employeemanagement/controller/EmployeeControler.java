package employeemanagement.controller;

import employeemanagement.model.Employee;
import employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeControler {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String goHome(Model model){
        //List<Employee> listEmployees = employeeService.getAllEmployees();
        //model.addAttribute("listEmployees",listEmployees);
       return findPaginated(1,model);
        //return "index";
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

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value="id") Long id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "updateEmployee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNo,pageSize);
        List<Employee> listEmployees = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("listEmployees",listEmployees);
        return "index";
    }
}
