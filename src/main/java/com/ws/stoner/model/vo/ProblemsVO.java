package com.ws.stoner.model.vo;

import com.ws.stoner.model.vo.problem.ProblemVO;

import java.util.List;

/**
 * Created by zhongkf on 2017/12/8
 */
public class ProblemsVO {

    private List<ProblemVO> problems;

    public ProblemsVO() {
    }

    public ProblemsVO(List<ProblemVO> problems) {
        this.problems = problems;
    }

    @Override
    public String toString() {
        return "ProblemsVO{" +
                "problems=" + problems +
                '}';
    }

    public List<ProblemVO> getProblems() {
        return problems;
    }

    public ProblemsVO setProblems(List<ProblemVO> problems) {
        this.problems = problems;
        return this;
    }
}
