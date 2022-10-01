package business.custom.impl;

import business.custom.CourseBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.impl.CourseDAOImpl;
import dto.CourseDTO;
import entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAOImpl courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);

    @Override
    public boolean add(CourseDTO courseDTO) throws Exception {
        return courseDAO.add(new Course(
                courseDTO.getId(),
                courseDTO.getTitle(),
                courseDTO.getDuration(),
                courseDTO.getSalary()
        ));
    }

    @Override
    public List<CourseDTO> findAll() throws Exception {
        List<Course> all = courseDAO.findAll();
        ArrayList<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course courses : all) {
            courseDTOS.add(new CourseDTO(
                    courses.getId(),
                    courses.getTitle(),
                    courses.getDuration(),
                    courses.getSalary()
            ));
        }
        return courseDTOS;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return courseDAO.delete(s);
    }

    @Override
    public boolean update(CourseDTO courseDTO) throws Exception {
        return courseDAO.update(new Course(
                courseDTO.getId(),
                courseDTO.getTitle(),
                courseDTO.getDuration(),
                courseDTO.getSalary()
        ));
    }
}
