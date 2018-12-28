package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

   private final OwnerService ownerService;
   private final VetService vetService;
   private final PetTypeService petTypeService;
   private final SpecialityService specialityService;
   private final VisitService visitService;


   public DataLoader(OwnerService ownerService, VetService vetService,
                     PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
      this.ownerService = ownerService;
      this.vetService = vetService;
      this.petTypeService = petTypeService;
      this.specialityService = specialityService;
      this.visitService = visitService;
   }

   @Override
   public void run(String... args) throws Exception {

      int petTypeCount = petTypeService.findAll().size();
      if( petTypeCount == 0) {
         loadData();
      }

   }

   private void loadData() {
      PetType dogPetType = new PetType();
      dogPetType.setName("Dog");
      PetType savedDogPetType = petTypeService.save(dogPetType);

      PetType catPetType = new PetType();
      catPetType.setName("Cat");
      PetType savedCatPetType = petTypeService.save(catPetType);

      Speciality radiology = new Speciality();
      radiology.setDescription("Radiology");
      Speciality savedRadiology = specialityService.save(radiology);

      Speciality surgery = new Speciality();
      radiology.setDescription("Surgery");
      Speciality savedSurgery = specialityService.save(surgery);

      Speciality dentistry = new Speciality();
      radiology.setDescription("Dentistry");
      Speciality savedDentistry = specialityService.save(dentistry);


      System.out.println("Loaded 2 Pet TYpes...............");

      Owner owner1 = new Owner();
      owner1.setFirstName("FirstOwner1");
      owner1.setLastName("LastOwner1");
      owner1.setAddress("1 Skyline");
      owner1.setCity("Toronto");
      owner1.setTelephone("416-111-2222");

      Pet dogPet = new Pet();
      dogPet.setPetType(savedDogPetType);
      dogPet.setOwner(owner1);
      dogPet.setBirthDate(LocalDate.now());

      owner1.getPets().add(dogPet);
      ownerService.save(owner1);


      Owner owner2 = new Owner();
      owner2.setFirstName("FirstOwner2");
      owner2.setLastName("LastOwner2");
      owner2.setAddress("2 Skyline");
      owner2.setCity("Toronto");
      owner2.setTelephone("416-222-3333");

      Pet catPet = new Pet();
      catPet.setPetType(savedCatPetType);
      catPet.setOwner(owner2);
      catPet.setBirthDate(LocalDate.now());

      owner2.getPets().add(catPet);
      ownerService.save(owner2);

      System.out.println("Loaded 2 owners...............");

      Visit catVisit = new Visit();
      catVisit.setPet(catPet);
      catVisit.setDate(LocalDate.now());
      catVisit.setDescription("Snizy cat");

      visitService.save(catVisit);

      Vet vet1 = new Vet();
      vet1.setFirstName("FirstVet1");
      vet1.setLastName("LastVet1");
      vet1.getSpecialities().add(savedRadiology);

      vetService.save(vet1);

      Vet vet2 = new Vet();
      vet2.setFirstName("FirstVet2");
      vet2.setLastName("LastVet2");
      vet2.getSpecialities().add(savedSurgery);

      vetService.save(vet2);

      System.out.println("Loaded 2 Vets................");
   }
}
