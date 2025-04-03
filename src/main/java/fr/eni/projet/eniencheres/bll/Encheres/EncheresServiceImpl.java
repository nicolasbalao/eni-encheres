package fr.eni.projet.eniencheres.bll.Encheres;

import fr.eni.projet.eniencheres.bo.Enchere;
import fr.eni.projet.eniencheres.dal.Encheres.EncheresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncheresServiceImpl implements EncheresService {
    private EncheresRepository encheresRepository;

    public EncheresServiceImpl(EncheresRepository encheresRepository) {
        super();
        this.encheresRepository = encheresRepository;
    }

    @Override
    public List<Enchere> consulterEncheres() {
        return encheresRepository.read();
    }

    @Override
    public List<Enchere> consulterEncheres(String articleName, String category, boolean isAchat, int achatSelect, int venteSelect, String pseudo) {
        return encheresRepository.read(articleName, category, isAchat, achatSelect, venteSelect, pseudo);
    }

    @Override
    public Enchere consulterEnchere(long id) {
        return encheresRepository.find(id);
    }
}
