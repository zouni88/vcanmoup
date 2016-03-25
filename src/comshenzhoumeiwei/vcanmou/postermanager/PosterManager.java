package comshenzhoumeiwei.vcanmou.postermanager;


public class PosterManager {
	public static final String TAG = "PosterManager";
	private static PosterManager mPosterManager;
	/**
     * 单例，将构造方法私有化
     */
    private PosterManager() {
    }

    /**
     * 工厂方法获取实例
     * 
     * @return OrderManager
     */
    public static PosterManager getInstance() {
        if (mPosterManager == null) {
        	mPosterManager = new PosterManager();
        }
        return mPosterManager;
    }
    
    
    
}
