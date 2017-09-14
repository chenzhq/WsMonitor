package com.ws.stoner.model.view.carousel;

import com.ws.stoner.model.view.problem.ProblemListVO;

import java.util.List;

/**
 * Created by zkf on 2017/9/14.
 */
public class ProblemsVO extends BlockVO {

    private List<ProblemListVO> problems;

    public ProblemsVO() {
    }

    public ProblemsVO(List<ProblemListVO> problems) {
        this.problems = problems;
    }

    public List<ProblemListVO> getProblems() {
        return problems;
    }

    public ProblemsVO setProblems(List<ProblemListVO> problems) {
        this.problems = problems;
        return this;
    }
}
