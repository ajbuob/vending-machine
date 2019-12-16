# vending-machine

## Summary 
Sample Java vending machine to support the following use cases:
* Ability to find the price of a specific product.
* Ability to purchase a product.
* Ability to add more product.

## Test
All test cases are located in src/test/java and can be run using the standard maven command (`mvn test`) in the project's root directory 

## Design/Notes
* `VendingMachine` and `ReloadableVendingMachine` interfaces used to support concrete `VendingMachineImpl` and `ReloadableVendingMachineImpl` implementations.
* `AbstractVendingMachine` contains base implementation and can be extended to create vending machines with more specific functionalities as desired.
* `VendingMachineFactory` used to create concrete implementations defined by an interface type.


