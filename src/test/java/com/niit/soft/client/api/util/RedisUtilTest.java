package com.niit.soft.client.api.util;

import com.niit.soft.client.api.ClientApiApplication;
import com.niit.soft.client.api.domain.model.schoolmate.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest(classes = {ClientApiApplication.class})
class RedisUtilTest {
    @Resource
    private RedisUtil redisUtil;

    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF"; //16进制能用到的所有字符 0-15
        char[] hexs = hexStr.toCharArray();//toCharArray() 方法将字符串转换为字符数组。
        int length = (hexStr.length() / 2);//1个byte数值 -> 两个16进制字符
        byte[] bytes = new byte[length];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            int position = i * 2;//两个16进制字符 -> 1个byte数值
            n = str.indexOf(hexs[position]) * 16;
            n += str.indexOf(hexs[position + 1]);
            // 保持二进制补码的一致性 因为byte类型字符是8bit的  而int为32bit 会自动补齐高位1  所以与上0xFF之后可以保持高位一致性
            //当byte要转化为int的时候，高的24位必然会补1，这样，其二进制补码其实已经不一致了，&0xff可以将高的24位置为0，低8位保持原样，这样做的目的就是为了保证二进制数据的一致性。
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    public static void main(String[] args) throws Exception {
//        PythonUtil.getSentiment("开开心心");
//        Properties props = new Properties();
//        props.put("python.home", "/Library/Frameworks/Python.framework/Versions/3.8/lib");
//        props.put("python.console.encoding", "UTF-8");
//        props.put("python.security.respectJavaAccessibility", "false");
//        props.put("python.import.site", "false");
//        Properties preprops = System.getProperties();
//        PythonInterpreter.initialize(preprops, props, new String[0]);
//
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.exec("a=[5,2,3,9,4,0]; ");
//
//        interpreter.exec("print(sorted(a));");

//        String a = "18";
//        String[] argss = new String[]{"python", "/Users/xiaobinggan/client_api/src/main/resources/python/dealWithHex.py", a};
//        Process process = Runtime.getRuntime().exec(argss);// 执行py文件
//
//        BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String str = null;
//        while ((str = bfr.readLine()) != null) {
//            System.out.println(str);
//        }
//        bfr.close();
//        process.waitFor();

//        Process proc;
//        String[] cmdArr = new String[]{"python", "/Users/xiaobinggan/client_api/src/main/resources/python/demo.py"};
//        proc = Runtime.getRuntime().exec(cmdArr);// 执行py文件
//        //用输入输出流来截取结果
//        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "UTF-8"));
//        String line = null;
//        while ((line = in.readLine()) != null) {
//            System.out.println(line);
//
////            String[] argss = new String[]{"python", "/Users/xiaobinggan/client_api/src/main/resources/python/dealWithHex.py", line};
////            Process process = Runtime.getRuntime().exec(argss);// 执行py文件
////
////            BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
////            String str = null;
////            while ((str = bfr.readLine()) != null) {
////                System.out.println(str);
////            }
////            bfr.close();
////            process.waitFor();
////
////            JSONArray objects = JSON.parseArray(line);
////            System.out.println(objects);
//        }
//        in.close();
//        proc.waitFor();

//        proc = Runtime.getRuntime().exec("python /Users/xiaobinggan/client_api/src/main/resources/python/demo.py");// 执行py文件
//        //用输入输出流来截取结果
//        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//        String line = null;
//        while ((line = in.readLine()) != null) {
//            System.out.println(line);
//        }
//        in.close();
//        proc.waitFor();


        int a = 18;
        int b = 23;
        String[] argss = new String[]{"python", "/Users/xiaobinggan/client_api/src/main/resources/python/demo2.py", String.valueOf(a), String.valueOf(b)};
        Process proc = Runtime.getRuntime().exec(argss);// 执行py文件

        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
        proc.waitFor();

//        interpreter.execfile("/Users/xiaobinggan/client_api/src/main/resources/python/chinese.py");
//        PyFunction pyFunction = interpreter.get("chinese", PyFunction.class);
//        PyObject pyobj = pyFunction.__call__();
//        System.out.println(pyobj);

//        PyFunction func = interpreter.get("add",
//                PyFunction.class);
//
//        int a = 100, b = 100;
//        PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));
//        System.out.println("anwser = " + pyobj.toString());

//        PySystemState sys = Py.getSystemState();
//        sys.path.add("/Users/xiaobinggan/IDEAProject/maven/org/python/jython-standalone/2.7.0");
//        interpreter.exec("import sys");
//        interpreter.exec("print sys.path");
//        interpreter.exec("path = /Users/xiaobinggan/IDEAProject/maven/org/python/jython-standalone/2.7.0");
//        interpreter.exec("sys.path.append(path)");
//        interpreter.exec("print sys.path");
//        InputStream filepy = new FileInputStream("/Users/xiaobinggan/client_api/src/main/resources/python/chinese.py");
//        interpreter.execfile(filepy);

    }

    public static String toStringHex2(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    @Test
    public void lSet() {
//        List<Message> messages = new ArrayList<>();
//        messages.add(Message.builder().pkMessageId(String.valueOf(new SnowFlake(1, 3).nextId()))
//                .fromId("1")
//                .toId("2")
//                .content("你好你好")
//                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
//                .gmtModified(Timestamp.valueOf(LocalDateTime.now())).build());
        redisUtil.lSet("fromIdtoId", Message.builder().pkMessageId(String.valueOf(new SnowFlake(1, 3).nextId()))
                .fromId("1")
                .toId("2")
                .content("你好你好")
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(Timestamp.valueOf(LocalDateTime.now())).build());
        System.out.println(redisUtil.lGet("fromIdtoId", 0, -1));
    }


}