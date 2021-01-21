package com.codegym.airbnb.service.apartment;

import com.codegym.airbnb.model.*;
import com.codegym.airbnb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements IApartmentService {
    @Autowired
    private IApartmentRepository apartmentRepository;

    @Autowired
    private IWardRepository wardRepository;

    @Autowired
    private IRentRepository rentRepository;

    @Override
    public Iterable<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment save(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @Override
    public Optional<Apartment> findById(Long id) {
        return apartmentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        apartmentRepository.deleteById(id);
    }

    @Override
    public void stopSelling(Long id) {
        Apartment apartmentById = apartmentRepository.findById(id).get();
        apartmentById.setStatus(3);
        apartmentRepository.save(apartmentById);
    }

    @Override
    public void repairSelling(Long id) {
        Apartment apartmentById = apartmentRepository.findById(id).get();
        apartmentById.setStatus(2);
        apartmentRepository.save(apartmentById);
    }

    @Override
    public void rentAgainApartment(Long id) {
        Apartment apartmentById = apartmentRepository.findById(id).get();
        apartmentById.setStatus(0);
        apartmentRepository.save(apartmentById);
    }

    @Override
    public void rentingApartment(Long id) {
        Apartment apartmentById = apartmentRepository.findById(id).get();
        apartmentById.setStatus(1);
        apartmentRepository.save(apartmentById);
    }

    @Override
    public Iterable<Apartment> findSevenApartment() {
        return apartmentRepository.findSevenApartment();
    }

    @Override
    public Iterable<Apartment> findAllByUser(User user) {
        return apartmentRepository.findAllByUser(user);
    }

    @Override
    public Iterable<Apartment> findAllByApartment_Id(Long id) {
        return apartmentRepository.findAllRentApartmentByUserId(id);
    }

    @Override
    public ArrayList<Apartment> findApartmentByAllCondition(SearchCondition searchCondition) {
        ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentRepository.findAll();
        if (searchCondition.getProvince() != null) {
            apartments = filterByAddress(apartments, getListWard(searchCondition));
        }
        if (searchCondition.getBathroom() != null) {
            apartments = filterByRoom(apartments, searchCondition.getBathroom(), "bathroom");
        }
        if (searchCondition.getBedroom() != null) {
            apartments = filterByRoom(apartments, searchCondition.getBedroom(), "bedroom");
        }
        if (searchCondition.getVipRoom() != null) {
            apartments = filterByRoom(apartments, searchCondition.getVipRoom(), "vipRoom");
        }
        if (searchCondition.getLuxuryRoom() != null) {
            apartments = filterByRoom(apartments, searchCondition.getLuxuryRoom(), "luxuryRoom");
        }
        if (searchCondition.getSingleRoom() != null) {
            apartments = filterByRoom(apartments, searchCondition.getSingleRoom(), "singleRoom");
        }
        if (searchCondition.getCoupleRoom() != null) {
            apartments = filterByRoom(apartments, searchCondition.getCoupleRoom(), "coupleRoom");
        }
        if (searchCondition.getPresidentRoom() != null) {
            apartments = filterByRoom(apartments, searchCondition.getPresidentRoom(), "presidentRoom");
        }
        if (searchCondition.getMinPrice() != null || searchCondition.getMaxPrice() != null) {
            apartments = filterByPrice(apartments, searchCondition.getMinPrice(), searchCondition.getMaxPrice());
        }
        if (searchCondition.getCheckIn() != null) {
            apartments = filterByCheckInAndCheckOut(apartments, searchCondition.getCheckIn(), searchCondition.getCheckOut());
        }
        return apartments;
    }

    private ArrayList<Long> getListWard(SearchCondition searchCondition) {
        ArrayList<Long> wards = new ArrayList<>();
        if (searchCondition.getDistrict() != null) {
            if (searchCondition.getWard() != null) {
                wards.add(wardRepository.findById(searchCondition.getWard()).get().getId());
            } else {
                for (Ward ward : wardRepository.findByDistrict_Id(searchCondition.getDistrict())) {
                    wards.add(ward.getId());
                }
            }
        } else {
            for (Ward ward : wardRepository.findByDistrict_ProvinceId(searchCondition.getProvince())) {
                wards.add(ward.getId());
            }
        }
        return wards;
    }

    private ArrayList<Apartment> filterByAddress(ArrayList<Apartment> apartments, ArrayList<Long> wards) {
        ArrayList<Apartment> arrayList = new ArrayList<>();
        for (Apartment apartment : apartments) {
            if (wards.contains(apartment.getWard().getId())) {
                arrayList.add(apartment);
            }
        }
        return arrayList;
    }

    private ArrayList<Apartment> filterByRoom(ArrayList<Apartment> apartments, Long room, String roomName) {
        ArrayList<Apartment> arrayList = new ArrayList<>();
        switch (roomName) {
            case "bathroom":
                for (Apartment apartment : apartments) {
                    if (apartment.getBathroom() == room) {
                        arrayList.add(apartment);
                    }
                }
                break;
            case "bedroom":
                for (Apartment apartment : apartments) {
                    if (apartment.getBedroom() == room) {
                        arrayList.add(apartment);
                    }
                }
                break;
            case "vipRoom":
                for (Apartment apartment : apartments) {
                    if (apartment.getVipRoom() == room) {
                        arrayList.add(apartment);
                    }
                }
                break;
            case "luxuryRoom":
                for (Apartment apartment : apartments) {
                    if (apartment.getLuxuryRoom() == room) {
                        arrayList.add(apartment);
                    }
                }
                break;
            case "singleRoom":
                for (Apartment apartment : apartments) {
                    if (apartment.getSingleRoom() == room) {
                        arrayList.add(apartment);
                    }
                }
                break;
            case "coupleRoom":
                for (Apartment apartment : apartments) {
                    if (apartment.getCoupleRoom() == room) {
                        arrayList.add(apartment);
                    }
                }
                break;
            case "presidentRoom":
                for (Apartment apartment : apartments) {
                    if (apartment.getPresidentRoom() == room) {
                        arrayList.add(apartment);
                    }
                }
                break;
        }

        return arrayList;
    }

    private ArrayList<Apartment> filterByPrice(ArrayList<Apartment> apartments, Long minPrice, Long maxPrice) {
        if (minPrice == null) {
            minPrice = 0L;
        }
        if (maxPrice == null) {
            maxPrice = Long.MAX_VALUE;
        }
        ArrayList<Apartment> arrayList = new ArrayList<>();
        for (Apartment apartment : apartments) {
            if (apartment.getValue() > minPrice && apartment.getValue() < maxPrice) {
                arrayList.add(apartment);
            }
        }
        return arrayList;
    }

    private ArrayList<Apartment> filterByCheckInAndCheckOut(ArrayList<Apartment> apartments, Date checkIn, Date checkOut) {
        ArrayList<Apartment> arrayList = new ArrayList<>();
        ArrayList<Rent> rents = (ArrayList<Rent>) rentRepository.findAll();

        for (Apartment apartment : apartments) {
            boolean isAvailable = true;
            for (Rent rent : rents) {
                if (checkIn.after(rent.getStartDate()) && checkOut.before(rent.getEndDate()) && apartment.getId().equals(rent.getApartment().getId())) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                arrayList.add(apartment);
            }
        }
        return arrayList;
    }

}
