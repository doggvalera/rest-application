package lv.ctco.student;

import lv.ctco.assigment.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;


    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> studentFindAll() {
        studentRepository.findAll();
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }


    @Transactional
    @RequestMapping(path = "/{ID}", method = RequestMethod.GET)
    public ResponseEntity<?> studentById(@PathVariable("ID") int id) {
        if (studentRepository.exists(id)) {
            return new ResponseEntity<>(studentRepository.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(studentRepository.findOne(id), HttpStatus.NOT_FOUND);
        }
    }


    @Transactional
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> studentRemoveAll() {
        studentRepository.deleteAll();
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(path = "/{ID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> studentDeleteById(@PathVariable("ID") int id) {
        if (studentRepository.exists(id)) {
            studentRepository.delete(id);
            return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> studentsPost(@RequestBody Student student) {
        studentRepository.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Transactional
    @RequestMapping(path = "/{ID}", method = RequestMethod.PUT)
    public ResponseEntity<?> studentChangeById(@PathVariable("ID") int id, @RequestBody Student student) {
        studentRepository.findOne(id).setName(student.getName());
        studentRepository.findOne(id).setSurname(student.getSurname());
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }


    @Transactional
    @RequestMapping(path = "/{ID}/assignment", method = RequestMethod.POST)
    public ResponseEntity<?> studentsAddAssignment(@PathVariable("ID") int id, @RequestBody Assignment assignment) {
        Student one = studentRepository.findOne(id);
        one.addAssignment(assignment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(path = "/{S_ID}/assignment/{A_ID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> studentsDeleteAssignment(@PathVariable("S_ID") int id, @PathVariable("A_ID") int idA) {
        Student one = studentRepository.findOne(id);
        for (int i = 0; i < one.getListAssignment().size(); i++) {
            if (one.getListAssignment().get(i).getId() == idA) {
                one.getListAssignment().remove(i);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @RequestMapping(path = "/{S_ID}/assignment/{A_ID}", method = RequestMethod.PUT)
    public ResponseEntity<?> studentsUpdateAssignment(@PathVariable("S_ID") int id, @RequestBody Assignment assignment, @PathVariable("A_ID") int idA) {
        Student one = studentRepository.findOne(id);
        for (int i = 0; i < one.getListAssignment().size(); i++) {
            if (one.getListAssignment().get(i).getId() == idA) {
                one.getListAssignment().get(i).setTitle(assignment.getTitle());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
