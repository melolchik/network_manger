package com.melolchik.networkmanager.network;

import android.content.Context;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Olga Melekhina on 08.07.2016.
 */
public class NetworkManager {

    private static NetworkManager instance;

    private static final int NUMBER_OF_CORES = 2;
    private static final int KEEP_ALIVE_TIME = 60;


    /**
     * The Executor.
     */
    protected ThreadPoolExecutor executor;
    /**
     * The Preloaded queue.
     */
    protected final BlockingQueue<Runnable> preloadedQueue;

    private final OkHttpClient mOkHttpClient;
    private Retrofit mCollegesRetrofit;
    private CollegesApiMethods mCollegesApiMethods;
    private final Context mContext;

    /**
     * Create instance.
     *
     * @param context the context
     */
    public static void createInstance(Context context){
        instance = new NetworkManager(context);
    }

    /**
     * Get instance network manager.
     *
     * @return the network manager
     */
    public static NetworkManager getInstance(){
        return instance;
    }

    private NetworkManager(Context context){
        mContext = context;
        //init Thread Pool
        preloadedQueue = new LinkedBlockingQueue<>();
        executor = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES + 1, KEEP_ALIVE_TIME, TimeUnit.SECONDS, preloadedQueue);
        executor.prestartCoreThread();

        // specify a okHttpClient to modify the default timeout
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();

        //init Colleges Retrofit
        mCollegesRetrofit = new Retrofit.Builder()
                .baseUrl(getCollegesServerAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();
        mCollegesApiMethods = mCollegesRetrofit.create(CollegesApiMethods.class);
    }

    /**
     * Execute task.
     *
     * @param runnable the runnable
     */
    public void executeTask(Runnable runnable){
        if (runnable == null) return;
        preloadedQueue.add(runnable);
    }


    protected String getCollegesServerAddress(){
        return "https://nearbycolleges.info/api/";
    }

    public CollegesApiMethods getCollegesApiMethods() {
        return mCollegesApiMethods;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}
