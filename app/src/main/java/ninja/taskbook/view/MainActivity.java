package ninja.taskbook.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ninja.taskbook.R;
import ninja.taskbook.presenter.MainPresenter;
import ninja.taskbook.view.group.GroupFragment;
import ninja.taskbook.view.login.LoginActivity;
import ninja.taskbook.view.notification.NotificationFragment;
import ninja.taskbook.view.profile.ProfileFragment;
import ninja.taskbook.view.setting.SettingFragment;
import ninja.taskbook.view.task.TaskFragment;
import ninja.taskbook.view.drawer.DrawerManager;
import ninja.taskbook.view.drawer.DrawerItem;

//----------------------------------------------------------------------------------------------------
public class MainActivity extends AppCompatActivity implements IMainView, DrawerManager.DrawerListener {

    //----------------------------------------------------------------------------------------------------
    static final int LOGIN_ACTIVITY_CODE = 1;

    //----------------------------------------------------------------------------------------------------
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private List<DrawerItem> mDrawerItems = new ArrayList<>();
    private DrawerManager mDrawerManager;
    private int mDrawerIndex = 0;
    private MainPresenter mMainPresenter = new MainPresenter(this);

    // Init
    //----------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initToolbar();
        initDrawer();

        login();
    }

    private void initData() {
        mMainPresenter.initDatabase(getContentResolver());
    }

    private void initToolbar() {
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void initDrawer() {
        mCoordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinator_layout);
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        LinearLayout mDrawer = (LinearLayout)findViewById(R.id.drawer);

        DrawerItem item = new DrawerItem(R.mipmap.drawer_item_home);
        mDrawerItems.add(item);
        DrawerItem item1 = new DrawerItem(R.mipmap.drawer_item_bags);
        mDrawerItems.add(item1);
        DrawerItem item2 = new DrawerItem(R.mipmap.drawer_item_book);
        mDrawerItems.add(item2);
        DrawerItem item3 = new DrawerItem(R.mipmap.drawer_item_movie);
        mDrawerItems.add(item3);
        DrawerItem item4 = new DrawerItem(R.mipmap.drawer_item_picture);
        mDrawerItems.add(item4);
        DrawerItem item5 = new DrawerItem(R.mipmap.drawer_item_drink);
        mDrawerItems.add(item5);
        DrawerItem item6 = new DrawerItem(R.mipmap.drawer_item_close);
        mDrawerItems.add(item6);

        mDrawerManager = new DrawerManager(this, mCoordinatorLayout, mToolbar, drawerLayout, mDrawer, mDrawerItems, this);
    }

    private void initContent() {
        mDrawerIndex = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new ProfileFragment())
                .commitAllowingStateLoss();
    }

    private void login() {
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(login, LOGIN_ACTIVITY_CODE);
    }

    //----------------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent settings = new Intent(MainActivity.this, LoginActivity.class);
            startActivityForResult(settings, 0);
            /*
            ContentValues values = new ContentValues();
            values.put("user_id", 0);
            getContentResolver().insert(DatabaseInfo.UserTable.CONTENT_URI, values);
            */
            /*
            Runnable task = new Runnable() {
                public void run() {
                    try {
                        int result = ((HelloService.Client) ThriftManager.getInstance().createClient(ThriftManager.ClientTypeEnum.CLIENT_HELLO.toString())).hi("123", "234", "345");
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(task).start();
            */
            /*
            String[] words = {"Hello", "Hi", "Aloha"};
            Observable.just(words)
                    .map(new Func1<String[], Integer>() {
                        @Override
                        public Integer call(String[] words) {
                            try {
                                HelloService.Client client = (HelloService.Client)ThriftManager.createClient(ThriftManager.ClientTypeEnum.CLIENT_HELLO.toString());
                                if (client != null)
                                    return client.hi(words[0], words[1], words[2]);
                            } catch (TException e) {
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Integer>() {
                        @Override
                        public void call(Integer result) {

                        }
                    });
                    */
            /*
            new Handler().postDelayed(new Runnable() {
            public void run() {
                NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, new Intent(getContext(), MainActivity.class), 0);
                Notification notify = new Notification.Builder(getContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker("TickerText")
                        .setContentTitle("Notification Title")
                        .setContentText("This is the notification message")
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS)
                        .setContentIntent(pendingIntent)
                        .setWhen(System.currentTimeMillis())
                        .setNumber(1)
                        .build();
                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                manager.notify(1, notify);
            }
        }, 5000);
            */

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //----------------------------------------------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LOGIN_ACTIVITY_CODE:
                initContent();
                break;
            default:
                break;
        }
    }

    //----------------------------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        Log.d("Main", "back");
        FragmentManager manager = getSupportFragmentManager();
        if (0 < manager.getBackStackEntryCount()) {
            manager.popBackStack();
        } else {
            login(); // Todo: exit
        }
    }

    //----------------------------------------------------------------------------------------------------
    /*
    public Bitmap takeFrameScreenShot() {
        // Todo: Thread
        Bitmap bitmap = Bitmap.createBitmap(mFrameLayout.getWidth(), mFrameLayout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mFrameLayout.draw(canvas);

        return bitmap;
    }
    */

    // DrawerListener
    //----------------------------------------------------------------------------------------------------
    @Override
    public void onDrawerItemClicked(int index) {
        FragmentManager manager = getSupportFragmentManager();
        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("Main", "drawer:" + count);
        for (int i = 0; i < count; i++) {
            manager.popBackStack();
        }

        if (mDrawerIndex != index) {
            // Todo: animation & save fragment
            switch (index) {
                case 0:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_layout, new ProfileFragment())
                            .commit();
                    break;
                case 1:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_layout, new GroupFragment())
                            .commit();
                    break;
                case 2:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_layout, new TaskFragment())
                            .commit();
                    break;
                case 3:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_layout, new NotificationFragment())
                            .commit();
                    break;
                case 4:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_layout, new SettingFragment())
                            .commit();
                    break;
                case 6:
                    mMainPresenter.logout();
                    login();
                    break;
                default:
                    break;
            }
        }

        mDrawerIndex = index;
    }
}
