package haveman.HaveMan;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName InputFileMakeTree
 * @Description: TODO
 * @Author Lenovo
 * @Date 2020/5/16 0:11
 */
public interface InputFileMakeTree {
    interface ReadFileByBytes {
        List<Byte> bytes = new ArrayList<>();

        void readFileByBytes();

        default void auto(Byte b){
            bytes.add(b);
        }

        default void auto(byte[] Bytes){
            for (byte b:Bytes)
                auto(b);
        }

        default List<Byte> getBytes() {
            readFileByBytes();
            return bytes;
        }
    }

    interface ReadFileByChars {
        List<Character> characters = new ArrayList<>();

        void readFileByChars();

        default void auto(Character character){
            characters.add(character);
        }

        default List<Character> getCharacters() {
            readFileByChars();
            return characters;
        }
    }

    interface ReadFileByString {
        List<String> strings = new ArrayList<>();

        void readFileByString();

        default void auto(String s){
            strings.add(s);
        }

        default void auto(List<String> Strings){
            strings.addAll(Strings);
        }

        default List<String> getString() {
            readFileByString();
            return strings;
        }
    }
}
