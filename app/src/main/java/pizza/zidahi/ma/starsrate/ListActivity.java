package pizza.zidahi.ma.starsrate;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pizza.zidahi.ma.starsrate.Adapter.StarsAdapter;
import pizza.zidahi.ma.starsrate.classe.Stars;
import pizza.zidahi.ma.starsrate.service.StarsService;

public class ListActivity extends AppCompatActivity {

    private List<Stars> stars;

    private RecyclerView recyclerView;
    private StarsAdapter starAdapter ;
    private StarsService service ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //getSupportActionBar().hide();
        System.out.println("helloooooooooooooooo");
        stars = new ArrayList<>();
        service = StarsService.getInstance();
        init();

        for(Stars s : service.findAll())
            System.out.println(s.toString());
        recyclerView = findViewById(R.id.recycle_view);
        //insérer le code
        starAdapter = new StarsAdapter(this, service.findAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter( starAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }

    public void init(){
        service.create(new Stars("lionnel messi", "https://s.france24.com/media/display/48615024-e4b0-11eb-9773-005056a90284/w:1280/p:16x9/1bb7e4c7fba86598de2f5df9df91cc53fbc8e8c6.webp", 3.5f));
          service.create(new Stars("Cristiano Ronaldo", "https://resize-parismatch.lanmedia.fr/rcrop/250,250/img/var/news/storage/images/paris-match/people-a-z/cristiano-ronaldo/5974922-5-fre-FR/Cristiano-Ronaldo.jpg", 3));
    service.create(new Stars("N'Golo Kanté",
                                                                      "https://www.gala.fr/imgre/fit/http.3A.2F.2Fprd2-bone-image.2Es3-website-eu-west-1.2Eamazonaws.2Ecom.2Fprismamedia_people.2F2018.2F06.2F13.2Fb25e576e-435b-4300-818e-0950eb3e90f3.2Ejpeg/170x170/quality/80/n-golo-kante.jpg", 5));
    service.create(new Stars("Brahim nekach", "https://img.kooora.com/?i=o%2Fp%2F47%2F352%2Fbrahim-nakach-1.png", 1));
 service.create(new Stars("lebron james", "https://cdn.i-scmp.com/sites/default/files/styles/landscape/public/d8/yp/images/lebron_afp.jpg?itok=JDQZHUkT&v=1537437905", 1));
  service.create(new Stars("steph curry", "https://fr.news24viral.com/wp-content/uploads/2020/05/Voici-combien-vaut-Steph-Curry.jpg", 5));
    service.create(new Stars("Rafael Nadal", "https://resize-parismatch.lanmedia.fr/rcrop/250,250/img/var/news/storage/images/paris-match/people-a-z/rafael-nadal/5972306-7-fre-FR/Rafael-Nadal.jpg", 1));}
}