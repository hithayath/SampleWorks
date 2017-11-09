package com.du.taskapp.ui.mvp;

import com.du.taskapp.data.Home.HomeResult;
import com.du.taskapp.data.Home.PayLoad;
import com.du.taskapp.main.BusProvider;
import com.du.taskapp.network.ApiError;
import com.du.taskapp.network.ApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Hithayath.
 */
public class HomePresenterTest {

    @Mock
    ApiService mApiService;

    @Mock
    HomeView mMockView;

    private HomePresenter mTestPresenter;

    @Mock
    HomeResult mTestResponse;

    private List<PayLoad> EMPTY_RESPONSE = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        mTestResponse = new HomeResult();
        List<PayLoad> payLoads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PayLoad payLoad = mock(PayLoad.class);
            payLoad.setTitle("Test Title - "+i);
            payLoad.setShortDesc("Test Short Desc - "+i);
            payLoad.setImagePath("Test Image path - "+i);
            boolean even = i % 2 == 0; //Even items active and visible.
            payLoad.setIsActive(even ? 1 : 0);
            payLoad.setIsVisible(even ? 1 : 0);
            payLoads.add(payLoad);
        }
        mTestResponse.setPayLoad(payLoads);
        mTestPresenter = new HomePresenterImpl(mApiService);
        mTestPresenter.onAttachView(mMockView);
    }

    @Test
    public void test_loadTiles() throws Exception {
        mTestPresenter.loadTiles();

        verify(mApiService).getHomeData();
        BusProvider.getInstance().post(mTestResponse);

        InOrder inOrder = Mockito.inOrder(mMockView);
        inOrder.verify(mMockView).showLoading(true);
        inOrder.verify(mMockView).showLoading(false);
        verify(mMockView).setData(mTestPresenter.applyFilterAndSort(mTestResponse));
    }

    @Test
    public void test_loadEmptyTiles() throws Exception {
        mTestPresenter.loadTiles();

        verify(mApiService).getHomeData();
        mTestResponse.setPayLoad(EMPTY_RESPONSE);
        BusProvider.getInstance().post(mTestResponse);

        InOrder inOrder = Mockito.inOrder(mMockView);
        inOrder.verify(mMockView).showLoading(true);
        inOrder.verify(mMockView).showLoading(false);
        verify(mMockView).setData(mTestPresenter.applyFilterAndSort(mTestResponse));
    }

    @Test
    public void test_onTileFindUsClicked() throws Exception {

        PayLoad clickedTile = new PayLoad();
        clickedTile.setTitle("someThing");
        clickedTile.setSectionId(10);

        mTestPresenter.onTileClicked(clickedTile);

        verify(mMockView).showFindUs(clickedTile.getTitle());
    }

    @Test
    public void test_onTileOthersClicked() throws Exception {

        PayLoad clickedTile = new PayLoad();
        clickedTile.setTitle("someThing");
        clickedTile.setSectionId(1);
        clickedTile.setExternalLink("Some link");

        mTestPresenter.onTileClicked(clickedTile);

        verify(mMockView).showWebView(clickedTile.getTitle(), clickedTile.getExternalLink());
    }

    @Test
    public void test_ErrorIsHandled() {

        mTestPresenter.loadTiles();

        verify(mApiService).getHomeData();

        final ApiError error = new ApiError("Test message", new Exception("Test Error"));
        BusProvider.getInstance().post(error);
        InOrder inOrder = Mockito.inOrder(mMockView);
        inOrder.verify(mMockView).showLoading(true);
        inOrder.verify(mMockView).showLoading(false);

        verify(mMockView).showErrorMessageForFailure(error.getMessage());

    }

    @After
    public void tearDown() throws Exception {
        mTestPresenter.onDetachView();
    }


}