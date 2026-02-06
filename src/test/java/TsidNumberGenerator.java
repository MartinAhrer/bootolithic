import io.hypersistence.tsid.TSID;

void main(String[] args) {
    int count = args.length > 0 ? Integer.parseInt(args[0]) : 1;
    for (int i = 0; i < count; i++) {
        TSID id = TSID.fast();
        IO.println(id.toLong());
        IO.println(id.toString());
    }
}
