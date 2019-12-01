package kypm.com.mascotapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import kypm.com.mascotapp.adaptador.SectionsPageAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 0;
    private Context mcontext = MainActivity.this;
    private BottomNavigationView bottomNavigationView;
    //private BottomNavigationViewHelper bottomNavigationViewHelper;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView nombreCabecera;

    TextView recuperar_nombreimg;
    //Bundle parametros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, " On create: Starting");

        findElemente();
        setUpBottomNavigationview();
        setUpViewPager();
        events();
    }

    private void findElemente() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        viewPager = (ViewPager) findViewById(R.id.view_pager_container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        recuperar_nombreimg = findViewById(R.id.txt_nombreimg);

    }

    private void setUpViewPager(){
        SectionsPageAdapter sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        sectionsPageAdapter.addFragment(new HomeFragment());
//        sectionsPageAdapter.addFragment(new RecuperadosFragment());
//
        viewPager.setAdapter(sectionsPageAdapter);
//
//        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.getTabAt(0).setText("Perdidos");
//        tabLayout.getTabAt(1).setText("Recuperados");
    }

    private void setUpBottomNavigationview() {
        //bottomNavigationViewHelper.setUpBottomNavigationView(bottomNavigationView);
        //bottomNavigationViewHelper.enableBottomNavigationView(bottomNavigationView, mcontext);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    private void events() {
        //nombreCabecera.setText("Buscar por Imagen");

        /*if (parametros != null){
            recuperar_nombreimg.setText("");
        }else {
            String recuperandoImg = parametros.getString("nombreImagen");
            recuperar_nombreimg.setText(recuperandoImg);
        }*/

    }
}
