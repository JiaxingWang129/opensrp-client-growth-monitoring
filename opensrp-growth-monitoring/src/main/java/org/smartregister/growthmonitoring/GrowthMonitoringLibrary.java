package org.smartregister.growthmonitoring;

import org.smartregister.Context;
import org.smartregister.growthmonitoring.repository.WeightRepository;
import org.smartregister.growthmonitoring.repository.ZScoreRepository;
import org.smartregister.repository.EventClientRepository;
import org.smartregister.repository.Repository;

/**
 * Created by koros on 2/3/16.
 */
public class GrowthMonitoringLibrary {

    private final Repository repository;
    private final Context context;

    private WeightRepository weightRepository;
    private ZScoreRepository zScoreRepository;
    private EventClientRepository eventClientRepository;
    private int applicationVersion;
    private int databaseVersion;


    private static GrowthMonitoringLibrary instance;

    public static void init(Context context, Repository repository, int applicationVersion, int databaseVersion) {
        if (instance == null) {
            instance = new GrowthMonitoringLibrary(context, repository, applicationVersion, databaseVersion);
        }
    }

    public static GrowthMonitoringLibrary getInstance() {
        if (instance == null) {
            throw new IllegalStateException(" Instance does not exist!!! Call " + GrowthMonitoringLibrary.class.getName() + ".init method in the onCreate method of your Application class ");
        }
        return instance;
    }

    private GrowthMonitoringLibrary(Context context, Repository repository, int applicationVersion, int databaseVersion) {
        this.repository = repository;
        this.context = context;
        this.applicationVersion = applicationVersion;
        this.databaseVersion = databaseVersion;
    }

    public Repository getRepository() {
        return repository;
    }

    public WeightRepository weightRepository() {
        if (weightRepository == null) {
            weightRepository = new WeightRepository(getRepository());
        }
        return weightRepository;
    }


    public ZScoreRepository zScoreRepository() {
        if (zScoreRepository == null) {
            zScoreRepository = new ZScoreRepository(getRepository());
        }

        return zScoreRepository;
    }

    public EventClientRepository eventClientRepository() {
        if (eventClientRepository == null) {
            eventClientRepository = new EventClientRepository(getRepository());
        }

        return eventClientRepository;
    }

    public Context context() {
        return context;
    }

    public int getApplicationVersion() {
        return applicationVersion;
    }

    public int getDatabaseVersion() {
        return databaseVersion;
    }
}
