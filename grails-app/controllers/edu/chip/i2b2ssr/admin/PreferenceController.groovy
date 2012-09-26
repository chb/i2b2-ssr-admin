package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.data.Preference

class PreferenceController {
    String menuName = "Edit Preferences"

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
            return Preference.all.first()
    }
}
