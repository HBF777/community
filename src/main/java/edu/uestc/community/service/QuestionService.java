package edu.uestc.community.service;

import edu.uestc.community.dto.PaginationDTO;
import edu.uestc.community.dto.QuestionDto;
import edu.uestc.community.mapper.QuestionMapper;
import edu.uestc.community.mapper.UserMapper;
import edu.uestc.community.model.Question;
import edu.uestc.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size){
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);
        Integer offset = size*(paginationDTO.getPage()-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDTO = new QuestionDto();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDtoList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDtoList);
        return paginationDTO;
    }
}
