package com.ws.stoner.service;

import com.ws.stoner.model.view.BriefProblemVO;

import java.util.List;

/**
 * Created by zkf on 2017/6/12.
 */
public interface FetchBriefService {

    List<BriefProblemVO> listBriefProblems();

}
