package allindevelopment.com.mooker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

/**
 * Created by Kevin on 30/05/15.
 */
public class Home extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    String user = null;
    String first_name = null;
    String adress = null;
    String phone = null;
    int fois = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Intent intent = getIntent();
        user = intent.getStringExtra("user_name");
        first_name = intent.getStringExtra("first_name");
        adress = intent.getStringExtra("adress");
        phone = intent.getStringExtra("phone");

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        if (savedInstanceState == null) {
            onNavigationDrawerItemSelected(0);
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        System.out.println("SYSTEME = " + position);

        switch (position) {
            case 0:
                mTitle = getString(R.string.title_section1);
                Fragment test = new Random_Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, test).commit();
                break;

            case 1:
                Bundle args2 = new Bundle();
                mTitle = getString(R.string.title_section2);


                Fragment test2 = new Random_Fragment();
                test2.setArguments(args2);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, test2).commit();
                break;

            case 2:
                mTitle = getString(R.string.title_section3);
                Fragment test3 = new Random_Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, test3).commit();
                break;
            case 3:
                mTitle = getString(R.string.title_section4);
                Fragment test40 = new Random_Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, test40).commit();
                break;
            case 4:
                mTitle = getString(R.string.title_section5);
                finish();
                break;
        }
        getSupportActionBar().setTitle(mTitle);
    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

