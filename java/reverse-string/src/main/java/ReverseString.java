class ReverseString {

    String reverse(String inputString) {
        int len = inputString.length();
        char[] output = new char[len];

        for (int i = 0; i < len; ++i) {
            output[len - 1 - i] = inputString.charAt(i);
        }
        return new String(output);
    }
}
