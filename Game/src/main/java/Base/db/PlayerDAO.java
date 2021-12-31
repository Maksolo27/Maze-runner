package Base.db;

import Base.objects.implementation.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerDAO {

    public Player findById(int id) {
        return HibernateUtils.createHibernateSession().get(Player.class, id);
    }

    public void save(Player user) {
        Session session = HibernateUtils.createHibernateSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

}
