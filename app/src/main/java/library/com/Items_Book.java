package library.com;

public class Items_Book {
   private String id, title, iamge, desc_book, desc_writer, link, namewriter,size,page;
   private int counter_download , view;

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getCounter_download() {
        return counter_download;
    }

    public void setCounter_download(int counter_download) {
        this.counter_download = counter_download;
    }

    public String getnamewriter() {
        return namewriter;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Items_Book() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Items_Book(String id, String title,int view, int counter_download,String size , String page, String iamge, String desc_book, String desc_writer, String link, String namewriter) {
        this.id = id;
        this.title = title;
        this.iamge = iamge;
        this.desc_book = desc_book;
        this.desc_writer = desc_writer;
        this.link = link;
        this.namewriter = namewriter;
        this.size = size;
        this.page = page;
        this.counter_download = counter_download;
        this.view = view;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIamge() {
        return iamge;
    }

    public void setIamge(String iamge) {
        this.iamge = iamge;
    }

    public String getDesc_book() {
        return desc_book;
    }

    public void setDesc_book(String desc_book) {
        this.desc_book = desc_book;
    }

    public String getDesc_writer() {
        return desc_writer;
    }

    public void setDesc_writer(String desc_writer) {
        this.desc_writer = desc_writer;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
