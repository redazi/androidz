package pizza.zidahi.ma.starsrate.service;

import java.util.ArrayList;
import java.util.List;

import pizza.zidahi.ma.starsrate.classe.Stars;
import pizza.zidahi.ma.starsrate.dao.IDao;

public class StarsService implements IDao<Stars> {

    private List<Stars> stars;
    private static StarsService instance;
    private StarsService() {
        this.stars = new ArrayList<>();
    }

    @Override
    public boolean create(Stars o) {
        return stars.add(o);
    }

    @Override
    public boolean update(Stars o) {
        for(Stars s : stars){
            if(s.getStar() == o.getId()){

                s.setImage(o.getImage());
                s.setNom(o.getNom());
                s.setStar(o.getStar());
            }
        }
       /* stars.remove(o.getId());
        stars.add(o.getId(),o);*/
        return true;

    }

    @Override
    public boolean delete(Stars o) {
        return stars.remove(o);
    }

    @Override
    public Stars findById(int id) {
        for(Stars f : stars){
            if(f.getId() == id)
                return f;
        }
        return null;
    }

    @Override
    public List<Stars> findAll() {
        return stars;
    }

    public synchronized static StarsService getInstance() {
        if(instance == null)
            instance = new StarsService();
        return instance;
    }
}
