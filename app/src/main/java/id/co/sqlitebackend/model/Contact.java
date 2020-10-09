package id.co.sqlitebackend.model;
public class Contact {
    int _id;
    String _name;
    String _address;

    public Contact() {
    }

    public Contact(int _id, String _name, String _address) {
        this._id = _id;
        this._name = _name;
        this._address = _address;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }
}
