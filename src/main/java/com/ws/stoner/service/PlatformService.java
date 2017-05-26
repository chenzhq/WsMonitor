package com.ws.stoner.service;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.StateNumDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/24.
 */
@Service
public interface PlatformService {
    int countUnknownPlatform();

    int countMaintenancePlatform();

    int countProblemPlatform() throws ServiceException;

    int countOkPlatform();

    List<StateNumDTO> countAllPlatform() throws ServiceException;
}
