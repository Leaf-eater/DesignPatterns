package SingletonPattern;

/**
 * Created by 宇航 on 2017/2/28.
 * 需求：创建一个注册表对象，保证它只能有一个实例
 */
public class ClassicsSingleton {
    public static void main(String[] args) {
        Register register = Register.createRegister();
        register.setId(3);
        register = Register.createRegister();   //因为已经实例化，所以构造方法不会被再次调用
        System.out.println(register.getId());
    }
}

class Register {
    private static Register register;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Register() {
        System.out.println("可以将Register进行初始化");
    }

    public static Register createRegister(){
        if (register == null) {
            return register = new Register();
        }else {
            return register;
        }

    }

}
