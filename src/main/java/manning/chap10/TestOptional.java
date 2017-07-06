package manning.chap10;

import java.util.Optional;

/**
 * Created by jbwang0106 on 2017/7/6.
 */
public class TestOptional {

    public static void main(String [] args) {

        Insurance insurance = new Insurance();
        //insurance.setName("平安");
        Car car = new Car();
        //1. 声明一个空的Optional
        Optional<Object> empty = Optional.empty();

        //2. 依据一个非空值创建Optional,car为空会报错
        Optional<Car> carOptional = Optional.of(car);

        //3. 可接受null的Optional
        Optional<Car> carOptional1 = Optional.ofNullable(car);

        Optional<Insurance> insuranceOptional = Optional.ofNullable(insurance);
        car.setInsurance(insuranceOptional);

        Person person = new Person();
        person.setCar(carOptional);

        Optional<Person> person1 = Optional.ofNullable(person);
        String name = getCarInsuranceName(person1);
        System.out.println(name);

    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("UNKONMN");
    }
}
