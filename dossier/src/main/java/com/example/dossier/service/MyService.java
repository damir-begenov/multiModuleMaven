//package com.example.dossier.dossier.service;
//
//import com.example.dossier.dossier.modelsDossier.*;
//import com.example.dossier.dossier.modelsRisk.*;
//import com.example.dossier.dossier.repositoryDossier.*;
//import com.example.dossier.extractor.Mv_fl_extractor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.sql.rowset.serial.SerialBlob;
//import java.lang.reflect.Field;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.util.stream.Collectors;
//
//
//@Service
//public class MyService {
//    @Autowired
//    QoldauRepo QoldauRepo;
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//    @Autowired
//    CommodityProducerRepo commodityProducerRepo;
//    @Autowired
//    TIpEntityRepo TIpEntityRepo;
//    @Autowired
//    BankrotRepo bankrotRepo;
//    @Autowired
//    MshRepo mshRepo;
//    @Autowired
//    OpgRepo opgRepo;
//    @Autowired
//    BlockEsfRepo block_esfRepo;
//    @Autowired
//    ConvictsTerminatedtByRehabRepo convicts_terminated_by_rehabRepo;
//    @Autowired
//    TaxOutEntityRepo taxOutEntityRepo;
//    @Autowired
//    CriminalsRepo criminalsRepo;
//    @Autowired
//    FlPensionContrRepo flPensionContrRepo;
//    @Autowired
//    MzEntityRepo MzEntityRepo;
//    @Autowired
//    WantedListRepo wantedListRepo;
//    @Autowired
//    FlPensionMiniRepo flPensionMiniRepo;
//    @Autowired
//    MilitaryAccounting2Repo MilitaryAccounting2Repo;
//    @Autowired
//    MvUlFounderFlRepo mvUlFounderFlRepo;
//    @Autowired
//    ConvictsJustifiedRepo convicts_justifiedRepo;
//    @Autowired
//    IpgoEmailEntityRepo IpgoEmailEntityRepo;
//    @Autowired
//    MvUlFounderUlRepo mvUlFounderUlRepo;
//    @Autowired
//    MvUlLeaderEntityRepo mvUlLeaderEntityRepo;
//    @Autowired
//    RegAddressUlEntityRepo RegAddressUlEntityRepo;
//    @Autowired
//    AdvocateListEntityRepo advocateListEntityRepo;
//    @Autowired
//    AuditorsListEntityRepo auditorsListEntityRepo;
//    @Autowired
//    BailiffListEntityRepo bailiffListEntityRepo;
//    @Autowired
//    AccountantListEntityRepo accountantListEntityRepo;
//    @Autowired
//    FirstCreditBureauEntityRepo FirstCreditBureauEntityRepo;
//    @Autowired
//    NewPhotoRepo newPhotoRepo;
//    @Autowired
//    private MvAutoFlRepo mvAutoFlRepo;
//    @Autowired
//    FpgTempEntityRepo fpgTempEntityRepo;
//    @Autowired
//    private MvFlRepo mv_FlRepo;
//    @Autowired
//    private OmnRepo omn_repos;
//    @Autowired
//    private OrphansRepo orphans_repo;
//    @Autowired
//    private AdmRepo admRepo;
//    @Autowired
//    private EquipmentRepo equipment_repo;
//    @Autowired
//    private MvRnOldRepo mv_rn_oldRepo;
//    @Autowired
//    private DormantRepo dormantRepo;
//
//    @Autowired
//    private FlRelativesRepository fl_relativesRepository;
//    @Autowired
//    private RegAddressFlRepo regAddressFlRepo;
//    @Autowired
//    private PdlRepo pdlReposotory;
//    @Autowired
//    private MvUlRepo mv_ul_repo;
//    @Autowired
//    private MvIinDocRepo mvIinDocRepo;
//    @Autowired
//    private UniversitiesRepo uniRepo;
//    @Autowired
//    private NdsEntityRepo ndsEntityRepo;
//    @Autowired
//    private SchoolRepo schoolRepo;
//    @Autowired
//    private FlContactsRepo flContactsRepo;
//    @Autowired
//    private militaryAccountRepo militaryAccountRepo;
//    @Autowired
//    MvIinDocRepo mv_iin_docRepo;
//    @Autowired
//    FlRiskServiceImpl flRiskService;
//    @Autowired
//    private MvUlLeaderRepository mvUlLeaderRepository;
//    @Autowired
//    private RegAddressUlEntityRepo regAddressUlEntityRepo;
//    @Autowired
//    private DismissalRepo dismissalRepo;
//    @Autowired
//    private ImmoralLifestlyeRepo immoral_lifestlyeRepo;
//    @Autowired
//    private BeneficiariesListRepo beneficiariesListRepo;
//    @Autowired
//    private ConvictsAbroadRepo convictsAbroadRepo;
//    @Autowired
//    private DrugAddictsRepo drugAddictsRepo;
//    @Autowired
//    private IncapacitatedRepo incapacitatedRepo;
//    @Autowired
//    private KuisRepo kuisRepo;
//
//
//
//    public List<SearchResultModelUl> searchUlByName(String name) {
//        List<MvUl> mvUls = mv_ul_repo.getUlsByName(name.replace("$", "%"));
//        List<SearchResultModelUl> list = new ArrayList<>();
//        for (MvUl l: mvUls) {
//            SearchResultModelUl res = new SearchResultModelUl();
//            res.setBin(l.getBin());
//            res.setName(l.getShort_name());
//            list.add(res);
//        }
//        return list;
//    }
//
//    public List<SearchResultModelFL> getWIthAddFields(HashMap<String, String> req) {
//        List<MvAutoFl> list = new ArrayList<>();
//        if (req.get("vin") != "") {
//            list =  mvAutoFlRepo.findBYVIN(req.get("vin"));
//        }
//        if (list.size() < 1) {
//            String sql = createAdditionSQL(req);
//            List<MvFl> fls = jdbcTemplate.query(sql, new Mv_fl_extractor());
//            System.out.println(sql);
//            List<SearchResultModelFL> result = findWithPhoto(fls);
//            return result;
//        } else {
//            List<MvFl> fls = new ArrayList<>();
//            for (MvAutoFl i: list) {
//                try {
//                    MvFl r = mv_FlRepo.getUserByIin(i.getIin());
//                    fls.add(r);
//                } catch (Exception e) {
//                }
//            }
//            List<SearchResultModelFL> result = findWithPhoto(fls);
//            return result;
//        }
//    }
//    private String createAdditionSQL(HashMap<String, String> req) {
//        String sql = "select * from ser.mv_fl where first_name like '" + req.get("i").replace('$', '%') + "' and  patronymic like '" + req.get("o").replace('$', '%') + "' and last_name like '" + req.get("f").replace('$', '%') + "' ";
//        if (req.get("dateFrom") != "") {
//            sql = sql + "AND toDate(birth_date, 'YYYY-MM-DD') > toDate('" + req.get("dateFrom") + "', 'YYYY-MM-DD') ";
//        }
//        if (req.get("dateTo") != "") {
//            sql = sql + "AND toDate(birth_date, 'YYYY-MM-DD') < toDate('" + req.get("dateTo") + "', 'YYYY-MM-DD') ";
//        }
//        if (req.get("gender") != "") {
//            sql = sql + "AND gender = '" + req.get("gender") + "' ";
//        }
//        if (req.get("nation") != "") {
//            sql = sql + "AND nationality_ru_name = '" + req.get("nation").toUpperCase() + "' ";
//        }
//        if (req.get("city") != "") {
//            sql = sql + "AND district = '" + req.get("city").toUpperCase() + "' ";
//        }
//        if (req.get("country") != "") {
//            sql = sql + "AND citizenship_ru_name = '" + req.get("country").toUpperCase() + "' ";
//        }
//        if (req.get("region") != "") {
//            sql = sql + "AND region = '" + req.get("region").toUpperCase() + "' ";
//        }
//        if (req.get("region") != "") {
//            sql = sql + "AND region = '" + req.get("region").toUpperCase() + "' ";
//        }
//        return sql;
//    }
//    public List<SearchResultModelFL> getByIIN_photo(String IIN) {
//        List<MvFl> fls = mv_FlRepo.getUsersByLike(IIN);
//        List<SearchResultModelFL> result = findWithPhoto(fls);
//        return result;
//    }
//    public List<SearchResultModelFL> getByDocNumber_photo(String doc_number) {
//        String iin = mvIinDocRepo.getIinByDoc_Number(doc_number);
//        List<MvFl> fls = mv_FlRepo.getUsersByLike(iin);
//        List<SearchResultModelFL> result = findWithPhoto(fls);
//        return result;
//    }
//
//    public List<SearchResultModelFL> getByPhone(String phone) {
//        List<String> iin = flContactsRepo.getByPhoneNumber(phone);
//        List<MvFl> fls = new ArrayList<>();
//        for (String ii: iin) {
//            MvFl person = mv_FlRepo.getUserByIin(ii);
//            fls.add(person);
//        }
//        List<SearchResultModelFL> result = findWithPhoto(fls);
//        return result;
//    }
//    public List<SearchResultModelFL> getByVinFl(String vin) {
//        List<String> iin = mvAutoFlRepo.getByVin(vin);
//        List<MvFl> fls = new ArrayList<>();
//        for (String ii: iin) {
//            MvFl person = mv_FlRepo.getUserByIin(ii);
//            fls.add(person);
//        }
//        try {
//            List<SearchResultModelFL> result = findWithPhoto(fls);
//            return result;
//
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return null;
//    }
//    public List<SearchResultModelUl> getByVinUl(String vin) {
//        String VIN_upper = vin.toUpperCase();
//        List<String> iin = mvAutoFlRepo.getByVin(VIN_upper);
//        List<SearchResultModelUl> list = new ArrayList<>();
//        if (iin.size() > 0) {
//            List<MvUl> mvUls = mv_ul_repo.getUsersByLike(iin.get(0));
//            for (MvUl l: mvUls) {
//                SearchResultModelUl res = new SearchResultModelUl();
//                res.setBin(l.getBin());
//                res.setName(l.getFull_name_rus());
//                list.add(res);
//            }
//            return list;
//        } else {
//            return list;
//        }
//
//    }
//
//    public List<SearchResultModelFL> getByDoc_photo(String IIN) {
//        List<MvIinDoc> fls = mv_iin_docRepo.getByDoc_number(IIN);
//        List<MvFl> fls1 = new ArrayList<>();
//        for(MvIinDoc flss : fls){
//            System.out.println(flss.getIin());
//            fls1 = mv_FlRepo.getUsersByLike(flss.getIin());
//        }
//        List<SearchResultModelFL> result = findWithPhoto(fls1);
//        return result;
//    }
//
//    public List<SearchResultModelFL> getByFIO_photo(String i, String o, String f) {
//        List<MvFl> fls = mv_FlRepo.getUsersByFIO(i, o, f);
//
//        List<SearchResultModelFL> result = findWithPhoto(fls);
//        return result;
//    }
//
//    private List<SearchResultModelFL> findWithPhoto(List<MvFl> fls) {
//        List<SearchResultModelFL> result = new ArrayList<>();
//        for (MvFl fl: fls) {
//            SearchResultModelFL person = new SearchResultModelFL();
//            person.setFirst_name(fl.getFirst_name());
//            person.setLast_name(fl.getLast_name());
//            person.setPatronymic(fl.getPatronymic());
//            person.setIin(fl.getIin());
//            tryAddPhoto(person, fl.getIin());
//
//            result.add(person);
//        }
//        return result;
//    }
//
//    private SearchResultModelFL tryAddPhoto(SearchResultModelFL fl, String IIN) {
//        try {
//            Optional<PhotoDb> flRawPhoto = newPhotoRepo.findById(IIN);
//            fl.setPhoto(flRawPhoto.get().getPhoto());
//            return fl;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return fl;
//    }
//    private NodesFL tryAddPhoto(NodesFL node, String IIN) {
//        try {
//            List<PhotoDb> photos = new ArrayList<>();
//            photos = newPhotoRepo.findAllByIinv(IIN);
//            List<PhotoDb> photoDbs = new ArrayList<>();
//            for(PhotoDb photoDb1: photos){
//                photoDbs.add(photoDb1);
//                node.setPhotoDbf(photoDbs);
//            }
//            return node;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return node;
//    }
//    private FlFirstRowDto tryAddPhoto(FlFirstRowDto fl, String IIN) {
//        try {
//            List<PhotoDb> photos = new ArrayList<>();
//            photos = newPhotoRepo.findAllByIinv(IIN);
//            List<PhotoDb> photoDbs = new ArrayList<>();
//            for(PhotoDb photoDb1: photos){
//                photoDbs.add(photoDb1);
//                fl.setPhotoDbf(photoDbs);
//            }
//            return fl;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return fl;
//    }
//    private Children tryAddPhoto(Children hierarchy, String IIN) {
//        try {
//            List<PhotoDb> photos = new ArrayList<>();
//            photos = newPhotoRepo.findAllByIinv(IIN);
//            List<PhotoDb> photoDbs = new ArrayList<>();
//            for(PhotoDb photoDb1: photos){
//                photoDbs.add(photoDb1);
//                hierarchy.image = Arrays.toString(photoDbs.get(0).getPhoto());
//            }
//            return hierarchy;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return hierarchy;
//    }
//    private Map<String, Object> getPropertyMap(Object obj) {
//        Map<String, Object> properties = new HashMap<>();
//
//        Field[] fields = obj.getClass().getDeclaredFields();
//        for (Field field: fields) {
//            try {
//                Object value = field.get(obj);
//                properties.put(field.getName(), value);
//            } catch (IllegalAccessException e){
////                e.printStackTrace();
//            }
//        }
//        return properties;
//    }
//
//
//
//    public FlRelativesLevelDto createHierarchyObject(String IIN) throws SQLException {
//        List<MvFl> myMv_fl =  mv_FlRepo.getUsersByLike(IIN);
//        Children hierarchy = new Children();
//        hierarchy.name = myMv_fl.get(0).getIin();
//        hierarchy.value = "MAIN";
//        tryAddPhoto(hierarchy, IIN);
//            List<FlRelativiesDTO> flRelativesDtos = new ArrayList<>();
//            FlRelativesLevelDto nodes = new FlRelativesLevelDto();
//
//            MvFl flRaw = mv_FlRepo.getUserByIin(IIN);
//
//            if(flRaw.getIin()!=null){
//                nodes.setIin(flRaw.getIin());
//            }
//
//           int mainQuintity = flRiskService.findFlRiskByIin(IIN).getQuantity();
//            //--Основной ФЛ
//            nodes.setName(IIN+", " + (flRaw.getLast_name()!=null?flRaw.getLast_name():"") +" " + (flRaw.getFirst_name()!=null?flRaw.getFirst_name():"") +" "
//                    + (flRaw.getPatronymic()!=null?flRaw.getPatronymic():"")+", Риски к-во: " + mainQuintity);
//
//        if(mainQuintity!=0){
//            nodes.setHaveRisk(true);
//        } else{
//            nodes.setHaveRisk(false);
//        }
//
//            nodes.setFio((flRaw.getLast_name()!=null?flRaw.getLast_name():"") +" " + (flRaw.getFirst_name()!=null?flRaw.getFirst_name():"") +" "
//                    + (flRaw.getPatronymic()!=null?flRaw.getPatronymic():""));
//
//
//              Optional<PhotoDb> flRawPhoto = newPhotoRepo.findById(IIN);
//
//             if (flRawPhoto.isPresent()) {
//            try {
//                nodes.setPhoto(new SerialBlob(flRawPhoto.get().getPhoto()));
//            } catch (SQLException e) {
//            }
//        }
//            List<FlRelativesLevelDto> relativesNodes = new ArrayList<>();
//            List<Object[]> flRelativesObj = fl_relativesRepository.findAllByIin(IIN);
//
//            FlRelativiesDTO relativesDto = new FlRelativiesDTO();
//            relativesDto.setParent_iin(nodes.getIin());
//            relativesDto.setParent_fio(nodes.getFio());
//            flRelativesDtos.add(relativesDto);
//
//            //--1-Круг
//            for (Object[] flRelObj:flRelativesObj.stream()
//                    .filter(objects -> objects[8].toString().equals("1"))
//                    .collect(Collectors.toList())){
//
//                FlRelativesLevelDto firstLevel = new FlRelativesLevelDto();
//                int firstLevelCnt = 0;
//                firstLevel.setName(flRelObj[2]+", " + flRelObj[3] +" "+flRelObj[4] +" " +flRelObj[5] +", " + flRelObj[0]);
//
//                firstLevel.setFio(flRelObj[3] +" "+flRelObj[4] +" " +flRelObj[5]);
//
//                List<FlRelativesLevelDto> relativesNodesList = new ArrayList<>();
//
//                if(flRelObj[2]!=null && !flRelObj[2].toString().equals("---")){
//
//                    if(flRelObj[2].toString().length()==12){
//                        Optional<PhotoDb> flRawPhoto1Level = newPhotoRepo.findById(flRelObj[2].toString());
//                        if (flRawPhoto1Level.isPresent()) {
//                            try {
//                                firstLevel.setPhoto(new SerialBlob(flRawPhoto1Level.get().getPhoto()));
//                            } catch (SQLException e) {
//                            }
//                        }
//                        firstLevelCnt = flRiskService.findFlRiskByIin(flRelObj[2].toString()).getQuantity();
//
//
//                    }
//
//                    firstLevel.setIin(String.valueOf(flRelObj[2]));
//
//                    FlRelativiesDTO firstLvlDto = new FlRelativiesDTO();
//                    firstLvlDto.setParent_iin(firstLevel.getIin());
//                    firstLvlDto.setParent_fio(firstLevel.getFio());
//                    flRelativesDtos.add(firstLvlDto);
//
//                    //--2-Круг
//                    for (Object[] rel2Level:flRelativesObj.stream().filter(objects -> objects[8].toString().equals("2"))
//                            .collect(Collectors.toList())){
//
//                        if(flRelObj[2].toString().equals(rel2Level[1].toString())){
//                            FlRelativesLevelDto secondLevel = new FlRelativesLevelDto();
//                            List<FlRelativesLevelDto> relativesNodesList3Level = new ArrayList<>();
//                            String relation="";
//                            int secondLevelCnt =0;
//                            if(rel2Level[19]!=null){
//                                relation=" ("+rel2Level[19]+")";
//                            }
//                            secondLevel.setName(rel2Level[2]+", "+rel2Level[3] +" "+rel2Level[4] +" " +rel2Level[5] +", " +rel2Level[0] +relation);
//                            secondLevel.setFio(rel2Level[3] +" "+rel2Level[4] +" " +rel2Level[5]);
//
//                            if(rel2Level[2].toString().length()==12){
//                                Optional<PhotoDb> flRawPhoto2Level = newPhotoRepo.findById(rel2Level[2].toString());
//                                if (flRawPhoto2Level.isPresent()) {
//                                    try {
//                                        secondLevel.setPhoto(new SerialBlob(flRawPhoto2Level.get().getPhoto()));
//                                    } catch (SQLException e) {
//                                    }
//                                }
//                                secondLevelCnt = flRiskService.findFlRiskByIin(rel2Level[2].toString()).getQuantity();
//                            }
//
//                            if(secondLevelCnt!=0){
//                                secondLevel.setHaveRisk(true);
//                            } else{
//                                secondLevel.setHaveRisk(false);
//                            }
//                            secondLevel.setName(secondLevel.getName() + ", Риски к-во: " + secondLevelCnt);
//
//
//                            if(rel2Level[2]!=null && !rel2Level[2].toString().equals("---")){
//                                secondLevel.setIin(String.valueOf(rel2Level[2]));
//
//                                //--3 - Круг
//                                for (Object[] rel3Level:flRelativesObj.stream().filter(objects -> objects[8].toString().equals("3"))
//                                        .collect(Collectors.toList())){
//
//                                    if(rel2Level[2].toString().equals(rel3Level[1].toString())) {
//                                        FlRelativesLevelDto thirdLevel = new FlRelativesLevelDto();
//                                        String relation3Level="";
//                                        if(rel3Level[19]!=null){
//                                            relation3Level=" ("+rel3Level[19]+")";
//                                        }
//                                        int thirdLevelCnt =0;
//                                        thirdLevel.setFio(rel3Level[3] +" "+rel3Level[4] +" " +rel3Level[5]);
//
//                                        if(rel3Level[2]!=null && !rel3Level[2].toString().equals("---")){
//                                            thirdLevel.setIin(String.valueOf(rel3Level[2]));
//
//                                            Optional<PhotoDb> flRawPhoto3Level = newPhotoRepo.findById(rel3Level[2].toString());
//                                            if (flRawPhoto3Level.isPresent()) {
//                                                try {
//                                                    thirdLevel.setPhoto(new SerialBlob(flRawPhoto3Level.get().getPhoto()));
//                                                } catch (SQLException e) {
//                                                }
//                                            }
//
//                                            thirdLevelCnt = flRiskService.findFlRiskByIin(rel3Level[2].toString()).getQuantity();
//                                        }
//
//                                        if(thirdLevelCnt!=0){
//                                            thirdLevel.setHaveRisk(true);
//                                        } else{
//                                            thirdLevel.setHaveRisk(false);
//                                        }
//                                        thirdLevel.setName(rel3Level[2]+", " + rel3Level[3] +" "+rel3Level[4] +" " +rel3Level[5] + ", " +rel3Level[0] + relation3Level +", Риски к-во: " + thirdLevelCnt);
//                                        if(!thirdLevel.getFio().equals("null null null")){
//
//                                            FlRelativiesDTO thirdLevelDto = new FlRelativiesDTO();
//                                            thirdLevelDto.setParent_iin(thirdLevelDto.getIin());
//                                            thirdLevelDto.setParent_fio(thirdLevelDto.getFio());
////                                           thirdLevelDto.setRelativeBirthDate(getBirthDate(relation3Level[6]));
//
//                                            boolean isRel = true;
//
//                                            if(!flRelativesDtos.isEmpty()){
//                                                for(FlRelativiesDTO rel: flRelativesDtos){
//                                                    if((rel.getParent_iin()!=null && thirdLevelDto.getIin()!=null) && rel.getParent_iin().equals(thirdLevelDto.getIin())){
//                                                        isRel=false;
//                                                        break;
//                                                    }
//
//                                                    if((rel.getParent_fio()!=null && thirdLevelDto.getFio()!=null) && ( rel.getParent_fio().equals(thirdLevelDto.getFio()) )){
//                                                        isRel=false;
//                                                        break;
//                                                    }
//                                                }
//                                            }
//
//                                            if(isRel){
//                                                flRelativesDtos.add(thirdLevelDto);
//                                                relativesNodesList3Level.add(thirdLevel);
//                                            }
//                                        }
//                                    }
//
//                                }
//                            }
//
//                            secondLevel.setChildren(relativesNodesList3Level);
//                            if(!secondLevel.getFio().equals("null null null")){
//
//                                boolean isRel = true;
//
//                                FlRelativiesDTO secondLvlDto = new FlRelativiesDTO();
//                                secondLvlDto.setParent_iin(secondLevel.getIin());
//                                secondLvlDto.setParent_fio(secondLevel.getFio());
////                            secondLvlDto.setBirthDate(getBirthDate(rel2Level[6]));
//                                if(!flRelativesDtos.isEmpty()){
//                                    for(FlRelativiesDTO rel: flRelativesDtos){
//                                        if((rel.getParent_iin()!=null && secondLevel.getIin()!=null) && rel.getParent_iin().equals(secondLevel.getIin())){
//                                            isRel=false;
//                                            break;
//                                        }
//
//                                        if((rel.getParent_fio()!=null && secondLevel.getFio()!=null) && ( rel.getParent_fio().equals(secondLevel.getFio()) )){
//                                            isRel=false;
//                                            break;
//                                        }
//                                    }
//                                }
//
//                                if(isRel){
//                                    flRelativesDtos.add(secondLvlDto);
//                                    relativesNodesList.add(secondLevel);
//                                }
//                            }
//                        }
//                    }
//                }
//
//                if(firstLevelCnt!=0){
//                    firstLevel.setHaveRisk(true);
//                } else{
//                    firstLevel.setHaveRisk(false);
//                }
//                firstLevel.setName(firstLevel.getName() + ", Риски к-во: " + firstLevelCnt);
//
//                firstLevel.setChildren(relativesNodesList);
//
//                if(!firstLevel.getFio().equals("null null null")){
//                    relativesNodes.add(firstLevel);
//                }
//            }
//            nodes.setChildren(relativesNodes);
////            return nodes;
////        }
//        return nodes;
//    }
//
//    public NodesFL getNode(String IIN){
//        NodesFL myNode = new NodesFL();
//        try {
//            List<MvUlLeader> mv_ul_leaders =  mvUlLeaderRepository.findAllByIin(IIN);
//            try {
//                myNode.setUl_leaderList(mv_ul_leaders);
//            } catch (Exception e) {
//                System.out.println("mv_ul_leader Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("mv_ul_leader WRAP Error:" + e);
//        }
//        try {
//            List<ImmoralLifestyle> amoral =  immoral_lifestlyeRepo.getImmoByIIN(IIN);
//            try {
//                myNode.setAmoral(amoral);
//            } catch (Exception e) {
//                System.out.println("amoral Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("amoral WRAP Error:" + e);
//        }
//        try {
//            List<Dismissal> dismissals =  dismissalRepo.getDismissalByIIN(IIN);
//            try {
//                myNode.setDismissals(dismissals);
//            } catch (Exception e) {
//                System.out.println("dismissals Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("dismissals WRAP Error:" + e);
//        }
//        try {
//            List<BeneficiariesList> beneficiariesLists =  beneficiariesListRepo.getBenByIIN(IIN);
//            try {
//                myNode.setBeneficiariesLists(beneficiariesLists);
//            } catch (Exception e) {
//                System.out.println("dismissals Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("dismissals WRAP Error:" + e);
//        }
//        try {
//            List<MvAutoFl> myMv_auto_fl =  mvAutoFlRepo.getUsersByLike(IIN);
//            try {
//                myNode.setMvAutoFls(myMv_auto_fl);
//            } catch (Exception e) {
//                System.out.println("mv_auto_fl Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("mv_auto_fl WRAP Error:" + e);
//        }
//        try {
//            List<MvFl> myMv_fl =  mv_FlRepo.getUsersByLike(IIN);
//            try {
//                myNode.setMvFls(myMv_fl);
//            } catch (Exception e) {
//                System.out.println("mv_fl Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("mv_fl WRAP Error:" + e);
//        }
//        try {
//            List<ConvictsAbroad> convictsAbroads =  convictsAbroadRepo.getConvictsAbroadByIIN(IIN);
//            try {
//                myNode.setConvictsAbroads(convictsAbroads);
//            } catch (Exception e) {
//                System.out.println("convictsAbroads Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("convictsAbroads WRAP Error:" + e);
//        }
//        try {
//            List<Incapacitated> incapacitateds =  incapacitatedRepo.getIncapacitatedByIIN(IIN);
//            try {
//                myNode.setIncapacitateds(incapacitateds);
//            } catch (Exception e) {
//                System.out.println("incapacitateds Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("incapacitateds WRAP Error:" + e);
//        }
//
//        try {
//            List<Kuis> kuis =  kuisRepo.getKuisByIIN(IIN);
//            try {
//                myNode.setKuis(kuis);
//            } catch (Exception e) {
//                System.out.println("kuis Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("kuis WRAP Error:" + e);
//        }
//        try {
//            List<DrugAddicts> drugAddicts =  drugAddictsRepo.getDrugAddictsByIIN(IIN);
//            try {
//                myNode.setDrugAddicts(drugAddicts);
//            } catch (Exception e) {
//                System.out.println("drugAddicts Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("drugAddicts WRAP Error:" + e);
//        }
//        try {
//            List<Omn> myOmn =  omn_repos.getUsersByLike(IIN);
//            try {
//                Omn myOmns =  omn_repos.getUsersByLikeIin_bins(IIN);
//                myOmn.add(myOmns);
//                myNode.setOmns(myOmn);
//            } catch (Exception e) {
//                System.out.println("myOmn Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("myOmn WRAP Error:" + e);
//        }
//        try {
//            List<Orphans> myOrphans =  orphans_repo.getUsersByLike(IIN);
//            try {
//                myNode.setOrphans(myOrphans);
//            } catch (Exception e) {
//                System.out.println("orphans Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("orphans WRAP Error:" + e);
//        }
//        try {
//            List<Bankrot> bankrots = bankrotRepo.getbankrotByByIIN(IIN);
//            try {
//                myNode.setBankrots(bankrots);
//            } catch (Exception e) {
//                System.out.println("bankrot Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("bankrot WRAP Error:" + e);
//        }
//        try {
//            List<ConvictsJustified> convictsJustifieds = convicts_justifiedRepo.getconvicts_justifiedByByIIN(IIN);
//            try {
//                myNode.setConvictsJustifieds(convictsJustifieds);
//            } catch (Exception e) {
//                System.out.println("convicts_justified Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("convicts_justified WRAP Error:" + e);
//        }
//        try {
//            List<ConvictsTerminatedByRehab> convictsTerminatedByRehabs = convicts_terminated_by_rehabRepo.getconvicts_terminated_by_rehabByByIIN(IIN);
//            try {
//                myNode.setConvictsTerminatedByRehabs(convictsTerminatedByRehabs);
//            } catch (Exception e) {
//                System.out.println("convicts_terminated_by_rehab Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("convicts_terminated_by_rehab WRAP Error:" + e);
//        }
//        try {
//            List<Criminals> criminals = criminalsRepo.getcriminalsByByIIN(IIN);
//            try {
//                myNode.setCriminals(criminals);
//            } catch (Exception e) {
//                System.out.println("criminals Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("criminals WRAP Error:" + e);
//        }
//        try {
//            List<Adm> MyAdm =  admRepo.getUsersByLike(IIN);
//            try {
//                myNode.setAdms(MyAdm);
//            } catch (Exception e) {
//                System.out.println("adm Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("adm WRAP Error:" + e);
//        }
//        try {
//            List<Dormant> myDormant =  dormantRepo.getUsersByLike(IIN);
//            try {
//                myNode.setDormants(myDormant);
//            } catch (Exception e) {
//                System.out.println("dormant Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("dormant WRAP Error:" + e);
//        }
//        try {
//            List<MilitaryAccounting2Entity> militaryAccounting2Entities = MilitaryAccounting2Repo.getUsersByLike(IIN);
//            try {
//                myNode.setMilitaryAccounting2Entities(militaryAccounting2Entities);
//            } catch (Exception e) {
//                System.out.println("MilitaryAccounting2Entity Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("MilitaryAccounting2Entity WRAP Error:" + e);
//        }
//        try {
//            List<MvRnOld> mvRnOlds = mv_rn_oldRepo.getUsersByLike(IIN);
//            myNode.setMvRnOlds(mvRnOlds);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<Equipment> myEquipment =  equipment_repo.getUsersByLike(IIN);
//            myNode.setEquipment(myEquipment);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
////            List<fl_relatives> initialRelatives = fl_relativesRepository.findAllByIin(IIN);
////            Set<fl_relatives> allRelativesSet = new HashSet<>(initialRelatives); // Initialize set with the initial relatives
////
////            for (fl_relatives relative : initialRelatives) {
////                List<fl_relatives> moreRelatives = fl_relativesRepository.findAllByIin(relative.getParent_iin());
////                for(fl_relatives moreRelativ : moreRelatives){
////                    if(moreRelativ.getRelative_type() == "Ребенок" &)
////                }
////                allRelativesSet.addAll(moreRelatives); // Add the fetched relatives to the set
////            }
////
////            List<fl_relatives> allRelatives = new ArrayList<>(allRelativesSet); // Convert the set back to a list
////            Iterator<fl_relatives> iterator = allRelatives.iterator();
////            Set<String> uniqueIds = new HashSet<>();
////
////            while(iterator.hasNext()) {
////                fl_relatives relative = iterator.next();
////                if(!uniqueIds.add(relative.getParent_iin())) {
////                    iterator.remove();
////                }
////                if(uniqueIds.add(IIN)){
////                    iterator.remove();
////                }
////            }
////            myNode.setFl_relatives(allRelatives); // Set the
//            List<Object[]> flRelativesObj;
//            flRelativesObj = fl_relativesRepository.findAllByIin(IIN);
//            List<FlRelativiesDTO> flRelativesDtoList = new ArrayList<>();
//            for (Object[] relatives:flRelativesObj) {
//
//                FlRelativiesDTO dto = new FlRelativiesDTO();
//                //--Фио
//                dto.setParent_fio(relatives[3] +" "+relatives[4] +" " +relatives[5]);
//                System.out.println(relatives[3] +" "+relatives[4] +" " +relatives[5]);
//                if(relatives[8]!=null){ //--Круг
//                    dto.setLevel((String) relatives[8]);
//                }
//
//                if(relatives[8].toString().equals("1")){ //--Статус родственника
//                    if(relatives[0]!=null){
//                        dto.setRelative_type(relatives[0].toString());
//                    }
//                } else if(relatives[8].toString().equals("2")) {
//                    if(relatives[0]!=null) {
//                        dto.setRelative_type(relatives[0] +" ("+relatives[19]+")");
//                    }
//                } else if (relatives[8].toString().equals("3")){
//                    String relation="";
//                    if(relatives[19]!=null){
//                        relation=" ("+relatives[19]+")";
//                    }
//                    if(relatives[0]!=null) {
//                        dto.setRelative_type(relatives[0] +relation);
//                    }
//                } else {
//                    if(relatives[0]!=null){
//                        dto.setRelative_type(String.valueOf(relatives[0]));
//                    }
//                }
//
//                //--Дата рождения
//                if(relatives[6]!=null) {
//                    if(relatives[6].toString().length()==10){
//                        try{
//                            dto.setParent_birth_date(String.valueOf(LocalDate.parse(relatives[6].toString())));
//                        }catch (Exception e){
//                            try {
//                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//                                LocalDate localDateTime = LocalDate.parse(relatives[6].toString(), formatter);
//                                dto.setParent_birth_date(String.valueOf(localDateTime));
//                            }catch (Exception ex){
//                            }
//                        }
//
//                    } else if(relatives[6].toString().length()==22){
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd:hh:mm:ss a");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[6].toString(), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setParent_birth_date(String.valueOf(localDate));
//                        }catch (Exception e){
//                        }
//                    } else if(relatives[6].toString().length()==24){
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy/MM/dd:hh:mm:ss a]");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[6].toString().substring(1,23), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setBirth_date(String.valueOf(localDate));
//                        }catch (Exception e){
//                        }
//                    }
//                }
//
//                //--Дата регистрация брака
//                if(relatives[10]!=null) {
//                    if(relatives[10].toString().length()==10){
//                        try{
//                            dto.setMarriage_reg_date(String.valueOf(LocalDate.parse(relatives[10].toString())));
//                        }catch (Exception e){
//                            try {
//                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//                                LocalDate localDateTime = LocalDate.parse(relatives[10].toString(), formatter);
//                                dto.setMarriage_reg_date(String.valueOf(localDateTime));
//                            }catch (Exception ex){
//                            }
//                        }
//                    } else if(relatives[10].toString().length()==22){
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd:hh:mm:ss a");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[10].toString(), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setMarriage_reg_date(String.valueOf(localDate));
//                        }catch (Exception e){
//                        }
//                    } else if(relatives[10].toString().length()==24){
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy/MM/dd:hh:mm:ss a]");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[10].toString().substring(1,23), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setMarriage_reg_date(String.valueOf(localDate));
//                        }catch (Exception e){
//                        }
//                    }
//                }
//
//                //--Дата Рассторжения брака
//                if(relatives[11]!=null && !relatives[11].toString().equals("(null)")) {
//                    if(relatives[11].toString().length()==10){
//                        try{
//                            dto.setMarriage_divorce_date(String.valueOf(LocalDate.parse(relatives[11].toString())));
//                        }catch (Exception e){
//                            try {
//                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//                                LocalDate localDateTime = LocalDate.parse(relatives[11].toString(), formatter);
//                                dto.setMarriage_divorce_date(String.valueOf(localDateTime));
//                            }catch (Exception ex){
//                            }
//                        }
//                    } else if(relatives[11].toString().length()==22) {
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd:hh:mm:ss a");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[11].toString(), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setMarriage_divorce_date(String.valueOf(localDate));
//                        } catch (Exception e){
//                        }
//                    } else if(relatives[11].toString().length()==24) {
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy/MM/dd:hh:mm:ss a]");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[11].toString().substring(1,23), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setMarriage_divorce_date(String.valueOf(localDate));
//                        }catch (Exception e){
//                        }
//                    }
//                }
//
//                //--ИИН родственника
//                if(relatives[2]!=null && !relatives[2].toString().equals("---")) {
//                    dto.setParent_iin(relatives[2].toString());
//                }
//
//                if(!dto.getParent_fio().equals("null null null")){
//                    boolean isRel = true;
//
//                    if(!flRelativesDtoList.isEmpty()){
//                        for(FlRelativiesDTO rel: flRelativesDtoList){
//                            if((rel.getParent_iin()!=null && dto.getParent_iin()!=null) && rel.getParent_iin().equals(dto.getParent_iin())){
//                                isRel=false;
//
//                                break;
//                            }
//
//                            if((rel.getParent_fio()!=null && dto.getParent_fio()!=null) && ( rel.getParent_fio().equals(dto.getParent_fio()) )){
//                                isRel=false;
//                                break;
//                            }
//
//                        }
//                    }
//
//                    if(isRel){
//                        flRelativesDtoList.add(dto);
//
//                    }
//                }
//            }
//              myNode.setFl_relatives(flRelativesDtoList);
//
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<RegAddressFl> addressFls = regAddressFlRepo.getByIIN(IIN);
//            myNode.setRegAddressFls(addressFls);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<String> flPensionContrs = flPensionContrRepo.getUsersByLikeCompany(IIN);
//            System.out.println(flPensionContrs);
//
//            List<FlPensionFinal> flPensionFinals = new ArrayList<>();
//            for(String flPension : flPensionContrs){
//                FlPensionFinal flPensionFinal = new FlPensionFinal();
//                List<Map<String, Object>> fl_pension_contrss = new ArrayList<>();
//                fl_pension_contrss = flPensionContrRepo.getAllByCompanies(IIN,flPension);
//                List<Map<String, Object>> r = flPensionContrRepo.findAmountOfAmountByKNP(IIN,flPension);
//                List<String> fff = flPensionMiniRepo.getAllByCompaniesYear(IIN,flPension);
//                flPensionFinal.setFlPensionMinis(fl_pension_contrss);
//                flPensionFinal.setNakoplenya(r);
//                flPensionFinal.setYears(fff);
//                flPensionFinal.setCompanyBin(flPension);
//                flPensionFinals.add(flPensionFinal);
////            System.out.println(findAmountOfAmountByKNPf);
//            }
//            myNode.setFlPensionContrs(flPensionFinals);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<Msh> mshes = mshRepo.getUsersByLike(IIN);
//            myNode.setMshes(mshes);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<IpgoEmailEntity> ipgoEmailEntities = IpgoEmailEntityRepo.getUsersByLike(IIN);
//            myNode.setIpgoEmailEntities(ipgoEmailEntities);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<FirstCreditBureauEntity> firstCreditBureauEntities = FirstCreditBureauEntityRepo.getUsersByLike(IIN);
//            myNode.setFirstCreditBureauEntities(firstCreditBureauEntities);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<TIpEntity> TIpEntity = TIpEntityRepo.getUsersByLike(IIN);
//            try {
//                myNode.setTIpEntity(TIpEntity);
//            } catch (Exception e) {
//                System.out.println("TIpEntity Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("TIpEntity WRAP Error:" + e);
//        }
//        try {
//            List<AccountantListEntity> accountantListEntities = accountantListEntityRepo.getUsersByLike(IIN);
//            try {
//                myNode.setAccountantListEntities(accountantListEntities);
//            } catch (Exception e) {
//                System.out.println("AccountantListEntity Error: " + e);
//            }
//        } catch (Exception e){
//            System.out.println("AccountantListEntity WRAP Error:" + e);
//        }
//        try {
//            List<AdvocateListEntity> advocateListEntities = advocateListEntityRepo.getUsersByLike(IIN);
//            myNode.setAdvocateListEntities(advocateListEntities);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<AuditorsListEntity> auditorsListEntities = auditorsListEntityRepo.getUsersByLike(IIN);
//            myNode.setAuditorsListEntities(auditorsListEntities);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<BailiffListEntity> bailiffListEntities = bailiffListEntityRepo.getUsersByLike(IIN);
//            myNode.setBailiffListEntities(bailiffListEntities);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<BlockEsf> blockEsfs = block_esfRepo.getblock_esfByIIN(IIN);
//            myNode.setBlockEsfs(blockEsfs);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<MvUlFounderFl> mvUlFounderFls = mvUlFounderFlRepo.getUsersByLikeIIN(IIN);
//            myNode.setMvUlFounderFls(mvUlFounderFls);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<NdsEntity> ndsEntities = ndsEntityRepo.getUsersByLike(IIN);
//            myNode.setNdsEntities(ndsEntities);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<MzEntity> mzEntities = MzEntityRepo.getopgByIIN(IIN);
//            myNode.setMzEntities(mzEntities);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<WantedListEntity> wantedListEntities =  wantedListRepo.getByIIN(IIN);
//            myNode.setWantedListEntities(wantedListEntities);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<CommodityProducer> commodityProducers = commodityProducerRepo.getiin_binByIIN(IIN);
//            myNode.setCommodityProducers(commodityProducers);
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            myNode.setContacts(flContactsRepo.findAllByIin(IIN));
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            myNode.setPdls(pdlReposotory.getByIIN(IIN));
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            myNode.setMvIinDocs(mvIinDocRepo.getByIIN(IIN));
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            myNode.setUniversities(uniRepo.getByIIN(IIN));
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
//        try {
//            myNode.setSchools(schoolRepo.getByIIN(IIN));
//        } catch (Exception e){
//            System.out.println("Error:" + e);
//        }
////        try {
////            myNode.setMillitaryAccounts(militaryAccountRepo.findAllByIin(IIN));
////        } catch (Exception e){
////            System.out.println("Error:" + e);
////        }
////        List<flPensionMini> flPensionContrs1 = new ArrayList<>();
////        List<String> CompanyNames = flPensionContrRepo.getUsersByLikeCompany(IIN);
//        myNode = tryAddPhoto(myNode,IIN);
//        return myNode;
//    }
//
//
//
//    public NodesFL getRelatives(String IIN){
//        NodesFL myNode = new NodesFL();
//        try{
//            List<Object[]> flRelativesObj;
//            flRelativesObj = fl_relativesRepository.findAllByIin(IIN);
//            List<FlRelativiesDTO> flRelativesDtoList = new ArrayList<>();
//            for (Object[] relatives:flRelativesObj) {
//
//                FlRelativiesDTO dto = new FlRelativiesDTO();
//                //--Фио
//                dto.setParent_fio(relatives[3] +" "+relatives[4] +" " +relatives[5]);
//                System.out.println(relatives[3] +" "+relatives[4] +" " +relatives[5]);
//                if(relatives[8]!=null){ //--Круг
//                    dto.setLevel((String) relatives[8]);
//                }
//
//                if(relatives[8].toString().equals("1")){ //--Статус родственника
//                    if(relatives[0]!=null){
//                        dto.setRelative_type(relatives[0].toString());
//                    }
//                } else if(relatives[8].toString().equals("2")) {
//                    if(relatives[0]!=null) {
//                        dto.setRelative_type(relatives[0] +" ("+relatives[19]+")");
//                    }
//                } else if (relatives[8].toString().equals("3")){
//                    String relation="";
//                    if(relatives[19]!=null){
//                        relation=" ("+relatives[19]+")";
//                    }
//                    if(relatives[0]!=null) {
//                        dto.setRelative_type(relatives[0] +relation);
//                    }
//                } else {
//                    if(relatives[0]!=null){
//                        dto.setRelative_type(String.valueOf(relatives[0]));
//                    }
//                }
//
//                //--Дата рождения
//                if(relatives[6]!=null) {
//                    if(relatives[6].toString().length()==10){
//                        try{
//                            dto.setParent_birth_date(String.valueOf(LocalDate.parse(relatives[6].toString())));
//                        }catch (Exception e){
//                            try {
//                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//                                LocalDate localDateTime = LocalDate.parse(relatives[6].toString(), formatter);
//                                dto.setParent_birth_date(String.valueOf(localDateTime));
//                            }catch (Exception ex){
//                            }
//                        }
//
//                    } else if(relatives[6].toString().length()==22){
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd:hh:mm:ss a");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[6].toString(), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setParent_birth_date(String.valueOf(localDate));
//                        }catch (Exception e){
//                        }
//                    } else if(relatives[6].toString().length()==24){
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy/MM/dd:hh:mm:ss a]");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[6].toString().substring(1,23), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setBirth_date(String.valueOf(localDate));
//                        }catch (Exception e){
//                        }
//                    }
//                }
//
//                //--Дата регистрация брака
//                if(relatives[10]!=null) {
//                    if(relatives[10].toString().length()==10){
//                        try{
//                            dto.setMarriage_reg_date(String.valueOf(LocalDate.parse(relatives[10].toString())));
//                        }catch (Exception e){
//                            try {
//                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//                                LocalDate localDateTime = LocalDate.parse(relatives[10].toString(), formatter);
//                                dto.setMarriage_reg_date(String.valueOf(localDateTime));
//                            }catch (Exception ex){
//                            }
//                        }
//                    } else if(relatives[10].toString().length()==22){
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd:hh:mm:ss a");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[10].toString(), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setMarriage_reg_date(String.valueOf(localDate));
//                        }catch (Exception e){
//                        }
//                    } else if(relatives[10].toString().length()==24){
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy/MM/dd:hh:mm:ss a]");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[10].toString().substring(1,23), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setMarriage_reg_date(String.valueOf(localDate));
//                        }catch (Exception e){
//                        }
//                    }
//                }
//
//                //--Дата Рассторжения брака
//                if(relatives[11]!=null && !relatives[11].toString().equals("(null)")) {
//                    if(relatives[11].toString().length()==10){
//                        try{
//                            dto.setMarriage_divorce_date(String.valueOf(LocalDate.parse(relatives[11].toString())));
//                        }catch (Exception e){
//                            try {
//                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//                                LocalDate localDateTime = LocalDate.parse(relatives[11].toString(), formatter);
//                                dto.setMarriage_divorce_date(String.valueOf(localDateTime));
//                            }catch (Exception ex){
//                            }
//                        }
//                    } else if(relatives[11].toString().length()==22) {
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd:hh:mm:ss a");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[11].toString(), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setMarriage_divorce_date(String.valueOf(localDate));
//                        } catch (Exception e){
//                        }
//                    } else if(relatives[11].toString().length()==24) {
//                        try {
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy/MM/dd:hh:mm:ss a]");
//                            LocalDateTime localDateTime = LocalDateTime.parse(relatives[11].toString().substring(1,23), formatter);
//                            LocalDate localDate = localDateTime.toLocalDate();
//                            dto.setMarriage_divorce_date(String.valueOf(localDate));
//                        }catch (Exception e){
//                        }
//                    }
//                }
//
//                //--ИИН родственника
//                if(relatives[2]!=null && !relatives[2].toString().equals("---")) {
//                    dto.setParent_iin(relatives[2].toString());
//                }
//
//                if(!dto.getParent_fio().equals("null null null")){
//                    boolean isRel = true;
//
//                    if(!flRelativesDtoList.isEmpty()){
//                        for(FlRelativiesDTO rel: flRelativesDtoList){
//                            if((rel.getParent_iin()!=null && dto.getParent_iin()!=null) && rel.getParent_iin().equals(dto.getParent_iin())){
//                                isRel=false;
//
//                                break;
//                            }
//
//                            if((rel.getParent_fio()!=null && dto.getParent_fio()!=null) && ( rel.getParent_fio().equals(dto.getParent_fio()) )){
//                                isRel=false;
//                                break;
//                            }
//
//                        }
//                    }
//
//                    if(isRel){
//                        flRelativesDtoList.add(dto);
//
//                    }
//                }
//            }
//            myNode.setFl_relatives(flRelativesDtoList);
//
//        } catch (Exception e) {
//            System.out.println("Error:" + e);
//        }
//        myNode = tryAddPhoto(myNode,IIN);
//        return myNode;
//    }
//
//
//    public List<SearchResultModelUl> searchResultUl(String bin) {
//        List<MvUl> mvUls = mv_ul_repo.getUsersByLike(bin);
//        List<SearchResultModelUl> list = new ArrayList<>();
//        for (MvUl l: mvUls) {
//            SearchResultModelUl res = new SearchResultModelUl();
//            res.setBin(l.getBin());
//            res.setName(l.getShort_name());
//            list.add(res);
//        }
//
//        return list;
//    }
//    public List<TaxOutEntity> taxOutEntities(String bin, PageRequest pageRequest){
//        Page<TaxOutEntity> taxOutEntityPage = taxOutEntityRepo.getUsersByLike(bin,pageRequest);
//        return taxOutEntityPage.getContent();
//    }
//    public List<Map<String, Object>> pensionEntityUl(String bin, String year, PageRequest pageRequest){
//        Page<Map<String,Object>> pens = flPensionContrRepo.getPension(bin, year, pageRequest);
//        return pens.getContent();
//    }
//    public List<Map<String,Object>> pensionEntityUl1(String bin, Double year, Integer page){
//        Integer offset = page * 10;
//        List<Map<String,Object>> pens = flPensionContrRepo.getPension1(bin, year, offset);
//        return pens;
//    }
//
//
//
//    public NodesUL getNodeUL(String BIN) {
//        NodesUL myNode = new NodesUL();
//        try {
//            List<MvUlFounderFl> mvUlFounderFls = mvUlFounderFlRepo.getUsersByLike(BIN);
//            myNode.setMvUlFounderFls(mvUlFounderFls);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<Bankrot> bankrots = bankrotRepo.getbankrotByByIIN(BIN);
//            myNode.setBankrots(bankrots);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<MvUl> mvUls = mv_ul_repo.getUsersByLike(BIN);
//            myNode.setMvUls(mvUls);
//            for(MvUl mv_ul : mvUls){
//                mv_ul.setFull_name_rus(mv_ul.getShort_name());
//            }
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            myNode.setFl_contacts(flContactsRepo.findAllByIin(BIN));
//        } catch (Exception e) {
//            System.out.println("Error:" + e);
//        }
//        try {
//            List<Adm> MyAdm = admRepo.getUsersByLikeBin(BIN);
//            myNode.setAdms(MyAdm);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<Dormant> myDormant = dormantRepo.getUsersByLike(BIN);
//            myNode.setDormants(myDormant);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            RegAddressUlEntity address = regAddressUlEntityRepo.findByBin(BIN);
//            myNode.setRegAddressUlEntities(address);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<Equipment> myEquipment = equipment_repo.getUsersByLike(BIN);
//            myNode.setEquipment(myEquipment);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<Omn> myOmns = omn_repos.getUsersByLikeIin_bin(BIN);
//            myNode.setOmns(myOmns);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<Msh> mshes = mshRepo.getUsersByLike(BIN);
//            myNode.setMshes(mshes);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<Criminals> criminals = criminalsRepo.getcriminalsByByIIN(BIN);
//            myNode.setCriminals(criminals);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<BlockEsf> blockEsfs = block_esfRepo.getblock_esfByIIN(BIN);
//            myNode.setBlockEsfs(blockEsfs);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<OpgEntity> opgEntities = opgRepo.getopgByIIN(BIN);
//            myNode.setOpgEntities(opgEntities);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<AccountantListEntity> accountantListEntities = accountantListEntityRepo.getUsersByLikeBIN(BIN);
//            myNode.setAccountantListEntities(accountantListEntities);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<NdsEntity> ndsEntities = ndsEntityRepo.getUsersByLike(BIN);
//            myNode.setNdsEntities(ndsEntities);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<FpgTempEntity> fpgTempEntities = fpgTempEntityRepo.getUsersByLike(BIN);
//            myNode.setFpgTempEntities(fpgTempEntities);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<Pdl> pdls = pdlReposotory.getByBin(BIN);
//            myNode.setPdls(pdls);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<QoldauSubsidy> q = QoldauRepo.getByIIN(BIN);
//            myNode.setQoldauSubsidy(q);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<CommodityProducer> commodityProducers = commodityProducerRepo.getiin_binByIIN(BIN);
//            myNode.setCommodityProducers(commodityProducers);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        try {
//            List<MvRnOld> mvRnOlds = mv_rn_oldRepo.getUsersByLike(BIN);
//            myNode.setMvRnOlds(mvRnOlds);
//        } catch (Exception e) {
//            System.out.println("Error: " + e);}
//            try {
//                RegAddressUlEntity address = regAddressUlEntityRepo.findByBin(BIN);
//                RegAddressUlEntity setRegUlNaOdnom = regAddressUlEntityRepo.regAddressNaOdnomMeste(address.getRegAddrRegionRu(), address.getRegAddrDistrictRu()
//                        , address.getRegAddrLocalityRu(), address.getRegAddrStreetRu(), address.getRegAddrBuildingNum(), BIN);
//                myNode.setRegUlNaOdnomMeste(setRegUlNaOdnom);
//                System.out.println(address.getRegAddrRegionKz() + " " + address.getRegAddrDistrictKz() + " " + address.getRegAddrBuildingNum() );
//            } catch (Exception e) {
//                System.out.println("Error: " + e);
//
////         }try {
////             List<TaxOutEntity> taxOutEntitiest = taxOutEntityRepo.getUsersByLike(BIN);
////             myNode.setTaxOutEntities(taxOutEntitiest);
////         } catch (Exception e) {
////             System.out.println("Error: " + e);
//            }
//            try {
//                List<MvAutoFl> mvAutoFls = mvAutoFlRepo.getUsersByLike(BIN);
//                myNode.setMvAutoFls(mvAutoFls);
//            } catch (Exception e) {
//                System.out.println("Error: " + e);
//            }
//
//            List<MvUlFounderUl> mvUlFounderUls = mvUlFounderUlRepo.getUsersByLike(BIN);
//            List<SvedenyaObUchastnikovUlEntity> svedenyaObUchastnikovUlEntities = new ArrayList<>();
//            for (MvUlFounderUl mvUlFUl : mvUlFounderUls) {
//                SvedenyaObUchastnikovUlEntity svedenyaObUchastnikovUlEntity = new SvedenyaObUchastnikovUlEntity();
//                svedenyaObUchastnikovUlEntity.setIin_bin(mvUlFUl.getFounderBin());
//                svedenyaObUchastnikovUlEntity.setFIOorUlName(mvUlFUl.getFounderNameRu());
//                svedenyaObUchastnikovUlEntity.setReg_date(mvUlFUl.getRegDate());
//                if (mvUlFUl.isCurrent()) {
//                    svedenyaObUchastnikovUlEntity.setIdentificator("Учредитель ЮЛ");
//                } else {
//                    svedenyaObUchastnikovUlEntity.setIdentificator("Учредитель ЮЛ (исторический)");
//                }
//                svedenyaObUchastnikovUlEntities.add(svedenyaObUchastnikovUlEntity);
//            }
//            List<MvUlLeaderEntity> mvUlLeaderEntities = mvUlLeaderEntityRepo.getUsersByLike(BIN);
//            for (MvUlLeaderEntity mvUlFUl : mvUlLeaderEntities) {
//                SvedenyaObUchastnikovUlEntity svedenyaObUchastnikovUlEntity = new SvedenyaObUchastnikovUlEntity();
//                svedenyaObUchastnikovUlEntity.setIin_bin(mvUlFUl.getIin());
//                svedenyaObUchastnikovUlEntity.setFIOorUlName(mvUlFUl.getLastName() + " " + mvUlFUl.getFirstName() + " " + mvUlFUl.getPatronymic());
//                svedenyaObUchastnikovUlEntity.setReg_date(mvUlFUl.getRegDate());
//                if (mvUlFUl.getCurrent() == true) {
//                    svedenyaObUchastnikovUlEntity.setIdentificator("Директор");
//                } else {
//                    svedenyaObUchastnikovUlEntity.setIdentificator("Директор (исторический)");
//                }
//                svedenyaObUchastnikovUlEntities.add(svedenyaObUchastnikovUlEntity);
//
//            }
////            List<Map<String, Object>> r = flPensionContrRepo.findAmountOfEmployeesOfEveryYear(BIN);
////            myNode.setPensionYearAndEmpNum(r);
//            myNode.setSvedenyaObUchastnikovUlEntities(svedenyaObUchastnikovUlEntities);
//            if (myNode.getOmns().size() == 0
//                    & myNode.getBankrots().size() == 0
//                    & myNode.getAdms().size() == 0
//                    & myNode.getOpgEntities().size() == 0
//                    & myNode.getCriminals().size() == 0
//                    & myNode.getBlockEsfs().size() == 0
//                    & myNode.getFpgTempEntities().size() == 0) {
//                myNode.setPerson_with_risk(false);
//            } else {
//                myNode.setPerson_with_risk(true);
//            }
////         List<FL_PENSION_FINAL> flPensionFinals = new ArrayList<>();
////         FL_PENSION_FINAL flPensionFinal = new FL_PENSION_FINAL();
////         flPensionFinal.setNakoplenya(flPensionContrRepo.findAmountOfEmployeesOfEveryYear(BIN));
////         flPensionFinals.add(flPensionFinal);
////         myNode.setFlPensionContrs(flPensionFinals);
//
////         for(String flPension : flPensionContrs){
////             List<flPensionMini> fl_pension_contrss = new ArrayList<>();
////             fl_pension_contrss = flPensionMiniRepo.getAllByCompanies(IIN,flPension);
////             List<String> fff = flPensionMiniRepo.getAllByCompaniesYear(IIN,flPension);
//////            System.out.println(flPensionContrRepo.findAmountOfAmountByKNP(IIN,flPension));
//////            Object findAmountOfAmountByKNPf = flPensionContrRepo.findAmountOfAmountByKNP(IIN,flPension);
//////            System.out.printf(String.valueOf(findAmountOfAmountByKNPf.getClass().getName()));
////             flPensionFinal.setFlPensionMinis(fl_pension_contrss);
////             flPensionFinal.setNakoplenya(r);
////             flPensionFinal.setYears(fff);
////             flPensionFinal.setCompanyBin(flPension);
////             flPensionFinals.add(flPensionFinal);
//////            System.out.println(findAmountOfAmountByKNPf);
////         }
////         myNode.setFlPensionContrs(flPensionFinals);
////         List<TaxOutEntity> taxOutEntities = taxOutEntityRepo.getUsersByLike(BIN);
//            //     myNode.setTaxOutEntities(taxOutEntities);
////         List<FL_PENSION_FINAL> flPensionFinals = new ArrayList<>();
////         List<Integer> adad = flPensionContrRepo.amountOfYears(BIN);
////         for(Integer add : adad){
////             FL_PENSION_FINAL flPensionFinal = new FL_PENSION_FINAL();
////             System.out.println(add);
////             flPensionFinal.setAmountOfEmp(flPensionContrRepo.amountOfEmp(BIN,add));
//
////             flPensionFinal.setNakoplenya(r);
////             flPensionFinal.setYear(add);
////             flPensionFinals.add(flPensionFinal);
////         }
////         myNode.setFlPensionContrs(flPensionFinals);
////        try {
////            Integer number = taxOutEntityRepo.getTaxAmount(BIN);
////            myNode.setTaxCount(number);
////        } catch (Exception e) {
////            System.out.println("Tax error: " + e);
////        }
//            return myNode;
//        }
//
//
//        public FlFirstRowDto getFlFirstRow(String IIN){
//        FlFirstRowDto flFirstRowDto = new FlFirstRowDto();
//            try {
//                List<MvFl> myMv_fl =  mv_FlRepo.getUsersByLike(IIN);
//                try {
//                    flFirstRowDto.setMvFls(myMv_fl);
//                } catch (Exception e) {
//                    System.out.println("mv_fl Error: " + e);
//                }
//            } catch (Exception e){
//                System.out.println("mv_fl WRAP Error:" + e);
//            }try {
//                List<MvIinDoc> mvIinDocs =  mvIinDocRepo.getByIIN(IIN);
//                try {
//                    flFirstRowDto.setMvIinDocList(mvIinDocs);
//                } catch (Exception e) {
//                    System.out.println("mvIinDocs Error: " + e);
//                }
//            } catch (Exception e){
//                System.out.println("mvIinDocs WRAP Error:" + e);
//            }try {
//                List<MvAutoFl> mvAutoFls =  mvAutoFlRepo.getUsersByLike(IIN);
//                try {
//                    flFirstRowDto.setMvAutoFls(mvAutoFls);
//                } catch (Exception e) {
//                    System.out.println("mvIinDocs Error: " + e);
//                }
//            } catch (Exception e){
//                System.out.println("mvIinDocs WRAP Error:" + e);
//            }try {
//                FLRiskDto flRiskDto =  flRiskService.findFlRiskByIin(IIN);
//                try {
//                    flFirstRowDto.setRiskPercentage(flRiskDto.getPercentage());
//                } catch (Exception e) {
//                    System.out.println("mvIinDocs Error: " + e);
//                }
//            } catch (Exception e){
//                System.out.println("mvIinDocs WRAP Error:" + e);
//            }
//            flFirstRowDto = tryAddPhoto(flFirstRowDto,IIN);
//
//            return flFirstRowDto;
//        }
//
//    }
