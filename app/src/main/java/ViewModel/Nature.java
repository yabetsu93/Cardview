package ViewModel;

/**
 * Created by jabespauya on 10/11/2017 AD.
 */

public class Nature {

    private String _mTitle;
    private String _mDescription;
    private String _mImage;


    public Nature(){}

    public Nature(String Image, String title, String description) {
        this._mDescription = description;
        this._mTitle = title;
        this._mImage = Image;
    }

    public String get_mTitle() {
        return _mTitle;
    }

    public void set_mTitle(String _mTitle) {
        this._mTitle = _mTitle;
    }

    public String get_mDescription() {
        return _mDescription;
    }

    public void set_mDescription(String _mDescription) {
        this._mDescription = _mDescription;
    }

    public String get_mImage() {
        return _mImage;
    }

    public void set_mImage(String _mImage) {
        this._mImage = _mImage;
    }

}
