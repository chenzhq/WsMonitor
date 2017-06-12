package com.ws.stoner.manager.impl;

import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.StateNumDTO;
import com.ws.stoner.manager.PlatformManager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/25.
 */
@Service
public class PlatformManagerImpl implements PlatformManager {
    @Override
    public int countUnknownPlatform() {
        return 0;
    }

    @Override
    public int countMaintenancePlatform() {
        return 0;
    }

    @Override
    public int countProblemPlatform() throws ManagerException {
        return 0;
    }

    @Override
    public int countOkPlatform() {
        return 0;
    }

    @Override
    public List<StateNumDTO> countAllPlatform() throws ManagerException {
        return null;
    }
}
