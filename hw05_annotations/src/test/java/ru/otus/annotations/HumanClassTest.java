package ru.otus.annotations;


public class HumanClassTest {
    private HumanClass human;

   @AfterAll
    static void end() {
        System.out.println("ending class test");
    }

    @Before
    void creatingHuman() {
            human = new HumanClass();
            System.out.println("a new human is created - " + human.getName());
    }

    @After
    void killingHuman() {
        human.dying();
        System.out.println("Human " + human.getName() + " is dead");
    }

    @BeforeAll
    static void begin() {
            System.out.println("starting class test");
    }

     @Test
    void setNameForHuman() throws Exception {
        human.setNameForKid("Peter");
        if (!human.getName().equals("Peter")) {
            throw new RuntimeException("Wrong name!");
        }
    }

    @Test
    void incrementingAge() throws Exception {
        human.gettingOlder(10);
        if (human.getAge() != 11) {
            throw new RuntimeException("Age didn't increment by 1");
        }
    }

    @Test
    void setNameForNamedKid() throws Exception {
        human.setNameForKid("Josh");
        human.setNameForKid("Peter");
        if (human.getName().equals("Peter")) {
            throw new RuntimeException("Already named human is renamed");
        }

    }

    @Test
    void getDeadHumanOlder() throws Exception {
        human.setAge(70);
        human.dying();
        human.gettingOlder(human.getAge());
        if (human.getAge() == 71) {
            throw new RuntimeException("dead men can't become older!");
        }
    }
}