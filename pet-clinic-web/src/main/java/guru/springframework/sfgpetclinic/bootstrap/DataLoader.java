package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

   private final OwnerService ownerService;
   private final VetService vetService;

   public DataLoader() {
      ownerService = new OwnerServiceMap();
      vetService = new VetServiceMap();
   }

   @Override
   public void run(String... args) throws Exception {

      Owner owner1 = new Owner();
      owner1.setId(1L);
      owner1.setFirstName("FirstOwner1");
      owner1.setLastName("LastOwner1");

      ownerService.save(owner1);

      Owner owner2 = new Owner();
      owner2.setId(2L);
      owner2.setFirstName("FirstOwner2");
      owner2.setLastName("LastOwner2");

      ownerService.save(owner2);

      System.out.println("Loaded 2 owners...............");

      Vet vet1 = new Vet();
      vet1.setId(1L);
      vet1.setFirstName("FirstOwner1");
      vet1.setLastName("LastOwner1");

      vetService.save(vet1);

      Vet vet2 = new Vet();
      vet2.setId(2L);
      vet2.setFirstName("FirstOwner2");
      vet2.setLastName("LastOwner2");

      vetService.save(vet2);

      System.out.println("Loaded 2 Vets................");

   }
}