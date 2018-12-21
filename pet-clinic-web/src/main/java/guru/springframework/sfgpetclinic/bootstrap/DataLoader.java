package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
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


   public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
      this.ownerService = ownerService;
      this.vetService = vetService;
      this.petTypeService = petTypeService;
   }

   @Override
   public void run(String... args) throws Exception {

      PetType dogPetType = new PetType();
      dogPetType.setName("Dog");
      PetType savedDogPetType = petTypeService.save(dogPetType);

      PetType catPetType = new PetType();
      catPetType.setName("Cat");
      PetType savedCatPetType = petTypeService.save(catPetType);

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

      Vet vet1 = new Vet();
      vet1.setFirstName("FirstVet1");
      vet1.setLastName("LastVet1");

      vetService.save(vet1);

      Vet vet2 = new Vet();
      vet2.setFirstName("FirstVet2");
      vet2.setLastName("LastVet2");

      vetService.save(vet2);

      System.out.println("Loaded 2 Vets................");

   }
}
