package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.data.Preference

class PreferenceController {
    String menuName = "Edit Preferences"
    Preference preference

    def index() {
        redirect(action: editPrefs, params: params)
    }


    def editPrefs = {
        [preference: getPreference()]

    }

    def update = {
        flash["message"] = "Preferences saved"
        Preference p = Preference.all.first()
        p.properties = params
        p.save(failOnError: true)
        redirect(action: editPrefs)


    }

    Preference getPreference() {
        if (Preference.all.size() == 0) {
            Preference p1 = new Preference().save(failOnError: true)
            return p1
        }
        else {
            return Preference.all.first()
        }

    }
}
