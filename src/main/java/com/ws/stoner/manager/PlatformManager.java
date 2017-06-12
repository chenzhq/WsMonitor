package com.ws.stoner.manager;

import com.ws.stoner.exception.ManagerException;
import com.ws.stoner.model.dto.StateNumDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenzheqi on 2017/5/24.
 */
@Service
public interface PlatformManager {
    int countUnknownPlatform();

    int countMaintenancePlatform();

    int countProblemPlatform() throws ManagerException;

    int countOkPlatform();

    List<StateNumDTO> countAllPlatform() throws ManagerException;
}
