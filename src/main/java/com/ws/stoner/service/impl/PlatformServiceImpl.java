package com.ws.stoner.service.impl;

import com.ws.stoner.exception.ServiceException;
import com.ws.stoner.model.dto.StateNumDTO;
import com.ws.stoner.service.PlatformService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/25.
 */
@Service
public class PlatformServiceImpl implements PlatformService {
    @Override
    public int countUnknownPlatform() {
        return 0;
    }

    @Override
    public int countMaintenancePlatform() {
        return 0;
    }

    @Override
    public int countProblemPlatform() throws ServiceException {
        return 0;
    }

    @Override
    public int countOkPlatform() {
        return 0;
    }

    @Override
    public List<StateNumDTO> countAllPlatform() throws ServiceException {
        return null;
    }
}
