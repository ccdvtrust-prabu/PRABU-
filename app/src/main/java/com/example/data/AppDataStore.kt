package com.example.data

import com.example.model.*

object AppDataStore {

    val DELTA_DISTRICTS = listOf(
        "Thanjavur (தஞ்சாவூர்)",
        "Tiruvarur (திருவாரூர்)",
        "Nagapattinam (நாகப்பட்டினம்)",
        "Mayiladuthurai (மயிலாடுதுறை)",
        "Cuddalore (கடலூர்)",
        "Pudukkottai (புதுக்கோட்டை)",
        "Tiruchirappalli (திருச்சிராப்பள்ளி)",
        "Ariyalur (அரியலூர்)",
        "Perambalur (பெரம்பலூர்)",
        "Other Districts (பிற மாவட்டங்கள்)"
    )

    val STATS = listOf(
        StatCard("4,850+", "Active Volunteers", "செயல்பாட்டு தன்னார்வலர்கள்", "👥"),
        StatCard("145+", "Waterbodies Restored", "தூர்வாரப்பட்ட நீர்நிலைகள்", "💧"),
        StatCard("120,000+", "Trees & Mangroves", "மரங்கள் & சதுப்புநிலங்கள்", "🌱"),
        StatCard("85+", "Delta Villages Served", "பயனடைந்த டெல்டா கிராமங்கள்", "🏘️"),
        StatCard("28", "Disaster Interventions", "பேரிடர் நிவாரண பணிகள்", "🚨")
    )

    val FOCUS_AREAS = listOf(
        FocusArea(
            id = "disaster",
            iconEmoji = "🌊",
            titleEn = "Disaster Management & Preparedness",
            titleTa = "பேரிடர் மேலாண்மை & தயார்நிலை",
            taglineEn = "Building resilient communities against cyclones and floods",
            taglineTa = "புயல், வெள்ளத்தை எதிர்கொள்ளும் சமுதாய தயார்நிலை",
            descriptionEn = "The Cauvery Delta is vulnerable to cyclones (like Gaja and Nivar) and riverine flooding. We train community first-responders, conduct village mock drills, and establish emergency evacuation networks.",
            descriptionTa = "டெல்டா மாவட்டங்கள் கஜா, நிவர் போன்ற புயல்களாலும் காவேரி வெள்ளத்தினாலும் தொடர்ந்து பாதிக்கப்படுகின்றன. கிராம தன்னார்வலர்களுக்கு பேரிடர் மீட்புப் பயிற்சி அளித்து, பாதுகாப்பான வெளியேற்ற நெறிமுறைகளை உருவாக்குகிறோம்.",
            keyInitiativesEn = listOf(
                "Village Disaster Management Committees (VDMC)",
                "Early warning dissemination via village WhatsApp networks",
                "Community evacuation and safe cyclone shelter mapping",
                "Pre-monsoon drainage clearing and bund reinforcement"
            ),
            keyInitiativesTa = listOf(
                "கிராம பேரிடர் மேலாண்மைக் குழுக்கள்",
                "வாட்ஸ்அப் மூலமாக முன்கூட்டியே புயல்/மழை எச்சரிக்கைகள்",
                "புயல் பாதுகாப்பு மையங்கள் & வெளியேற்றப் பாதை வரைபடம்",
                "மழைக்காலத்திற்கு முன் வடிகால் மற்றும் கரைகள் பலப்படுத்துதல்"
            ),
            impactTargetEn = "Empowering 100 coastal & low-lying villages with trained emergency volunteer squads.",
            impactTargetTa = "100 கடலோர மற்றும் தாழ்வான கிராமங்களில் பயிற்சி பெற்ற அவசரகால மீட்புக் குழுக்கள்."
        ),
        FocusArea(
            id = "water_security",
            iconEmoji = "💧",
            titleEn = "Drinking Water Security",
            titleTa = "பாதுகாப்பான குடிநீர் பாதுகாப்பு",
            taglineEn = "Ensuring safe, potable water free from salinity and contamination",
            taglineTa = "உப்புத்தன்மை மற்றும் நச்சற்ற சுத்தமான குடிநீர் உறுதி",
            descriptionEn = "Many coastal delta hamlets suffer from seawater intrusion, high TDS, and contaminated groundwater. We conduct water testing, rehabilitate non-functional community RO systems, and promote household safe-storage.",
            descriptionTa = "கடலோரப் பகுதிகளில் கடல்நீர் உட்புகுதல் மற்றும் அதிக TDS குடிநீர் பிரச்சினைகள் அதிகம். குடிநீர் தர பரிசோதனை, பழுதான சுத்திகரிப்பு நிலையங்கள் சீரமைப்பு மற்றும் வீட்டு குடிநீர் பாதுகாப்பை ஊக்குவிக்கிறோம்.",
            keyInitiativesEn = listOf(
                "Free village water TDS and bacteriological testing camps",
                "Community RO plant maintenance audits & revitalizations",
                "Safe school drinking water tanks and filtration drives",
                "Salinity intrusion monitoring in coastal aquifers"
            ),
            keyInitiativesTa = listOf(
                "இலவச கிராம நீர் TDS மற்றும் தரப் பரிசோதனை முகாம்கள்",
                "கிராம பஞ்சாயத்து குடிநீர் சுத்திகரிப்பு நிலையங்கள் ஆய்வு & பராமரிப்பு",
                "பள்ளி மாணவர்களுக்கான பாதுகாப்பான குடிநீர் தொட்டிகள்",
                "நிலத்தடி நீரில் கடல்நீர் கலப்பு கண்காணிப்பு"
            ),
            impactTargetEn = "Safe drinking water access for 25,000+ rural school students and villagers.",
            impactTargetTa = "25,000-க்கும் மேற்பட்ட பள்ளி மாணவர்கள் மற்றும் கிராம மக்களுக்கு பாதுகாப்பான குடிநீர்."
        ),
        FocusArea(
            id = "environment",
            iconEmoji = "🌱",
            titleEn = "Environment & Climate Action",
            titleTa = "சுற்றுச்சூழல் & காலநிலை நடவடிக்கை",
            taglineEn = "Preserving green canopy and climate resilience across the delta",
            taglineTa = "பசுமைப் போர்வையை அதிகரித்தல் & காலநிலை மாற்றம் தணிப்பு",
            descriptionEn = "Restoring native biodiversity through mass native tree plantations, palm tree planting on canal bunds, agroforestry awareness for farmers, and micro-forest (Miyawaki) creations in schools.",
            descriptionTa = "டெல்டா பகுதிகளில் நாட்டு மரங்கள் நடுதல், பாசனக் கால்வாய் கரைகளில் பனை மரங்கள் நடுதல், விவசாயிகளுக்கு மரப்பயிர்கள் விழிப்புணர்வு மற்றும் குறுங்காடுகள் உருவாக்குதல்.",
            keyInitiativesEn = listOf(
                "Cauvery canal bund Palmyrah (பனை) seed sowing to prevent soil erosion",
                "Native shade & fruit trees in government schools and public lands",
                "Farmer climate adaptation workshops and organic mulching",
                "Green youth clubs for carbon footprint awareness"
            ),
            keyInitiativesTa = listOf(
                "கால்வாய் கரைகளில் மண் அரிப்பைத் தடுக்க பனை விதைகள் நடுதல்",
                "அரசுப் பள்ளிகளில் நிழல் மற்றும் பழ மரக்கன்றுகள் நடும் இயக்கம்",
                "விவசாயிகளுக்கு காலநிலை மாற்ற எதிர்கொள்ளல் பயிற்சி",
                "பள்ளி, கல்லூரி மாணவர்களுக்கான பசுமை மன்றங்கள்"
            ),
            impactTargetEn = "150,000 native saplings and 50,000 palm seeds along vulnerable river channels.",
            impactTargetTa = "1.5 லட்சம் நாட்டு மரக்கன்றுகள் மற்றும் 50,000 பனை விதைகள் நடுதல்."
        ),
        FocusArea(
            id = "education",
            iconEmoji = "🏫",
            titleEn = "Education & Child Development",
            titleTa = "கல்வி & குழந்தைகள் மேம்பாடு",
            taglineEn = "Empowering rural children with eco-literacy and emergency life skills",
            taglineTa = "கிராமப்புறக் குழந்தைகளுக்கு கல்வி, சுற்றுச்சூழல் & பாதுகாப்பு அறிவு",
            descriptionEn = "Supporting government school students in remote delta villages through disaster safety education, science & ecology kits, digital learning labs, and sports equipment.",
            descriptionTa = "கிராமப்புற அரசுப் பள்ளி மாணவர்களுக்கு பேரிடர் பாதுகாப்பு விழிப்புணர்வு, அறிவியல் உபகரணங்கள், சூழலியல் கல்வி மற்றும் கற்றல் உபகரணங்கள் வழங்குதல்.",
            keyInitiativesEn = listOf(
                "School Disaster Safety Drills & First-aid demo camps",
                "Environmental library corners & Nature study camps",
                "Education sponsorship for cyclone-affected children",
                "Child safety, sanitation and menstrual health education"
            ),
            keyInitiativesTa = listOf(
                "பள்ளிகளில் பேரிடர் தற்காப்பு ஒத்திகை மற்றும் முதலுதவிப் பயிற்சி",
                "சுற்றுச்சூழல் புத்தக மூலை மற்றும் இயற்கை விழிப்புணர்வு",
                "புயலால் பாதிக்கப்பட்ட மாணவர்களுக்கு கல்வி உதவித்தொகை",
                "குழந்தைகள் சுகாதாரம் மற்றும் பாதுகாப்பு விழிப்புணர்வு"
            ),
            impactTargetEn = "Reaching 60+ rural schools and 12,000+ students with life-safety skills.",
            impactTargetTa = "60-க்கும் மேற்பட்ட அரசுப் பள்ளிகள் மற்றும் 12,000+ மாணவர்கள் பயனடைதல்."
        ),
        FocusArea(
            id = "mangrove_waterbody",
            iconEmoji = "🌳",
            titleEn = "Waterbody & Mangrove Restoration",
            titleTa = "நீர்நிலை & சதுப்புநில அலையாத்திக் காடுகள் மீட்டெடுப்பு",
            taglineEn = "Nature-based bio-shields against coastal cyclones and sea surges",
            taglineTa = "கடல் சீற்றங்களைத் தடுக்கும் அலையாத்தி காடுகள் & கிராமக் குளங்கள் புனரமைப்பு",
            descriptionEn = "Restoring silted village tanks (ஊரணிகள் / குளங்கள்) and expanding coastal mangrove buffers in Muthupet, Pichavaram and coastal estuaries that protect inland communities during storms.",
            descriptionTa = "தூர்ந்துபோன கிராமத்து ஊரணிகள், ஏரிகளை தூர்வாருதல் மற்றும் முத்துப்பேட்டை, பிச்சாவரம் போன்ற கடலோர சதுப்புநில அலையாத்திக் காடுகளைப் பாதுகாத்தல்.",
            keyInitiativesEn = listOf(
                "Community desilting of heritage village ponds and inlet channels",
                "Planting Rhizophora and Avicennia mangrove saplings in tidal lagoons",
                "Vetiver grass plantation on pond bunds to stabilize banks",
                "Protection of water storage structures for summer resilience"
            ),
            keyInitiativesTa = listOf(
                "கிராம ஊரணிகள் மற்றும் வரத்துக் கால்வாய்களை பொதுமக்களுடன் தூர்வாருதல்",
                "கடலோரக் காயல்களில் அலையாத்திக் கன்றுகள் நடுதல்",
                "குளக்கரைகளில் மண் சரிவை தடுக்க வெட்டிவேர் நடுதல்",
                "கோடை காலத்திற்குத் தேவையான நீர்நிலைகளைத் தயார்படுத்துதல்"
            ),
            impactTargetEn = "Revitalizing 200 village tanks and planting 50,000 mangrove propagules.",
            impactTargetTa = "200 கிராமக் குளங்கள் புனரமைப்பு மற்றும் 50,000 சதுப்புநிலக் கன்றுகள் நடுதல்."
        ),
        FocusArea(
            id = "community_dev",
            iconEmoji = "👥",
            titleEn = "Community Development",
            titleTa = "சமுதாய வளர்ச்சி & தன்னிறைவு",
            taglineEn = "Strengthening grassroots leadership, women groups and rural livelihoods",
            taglineTa = "கிராமப்புற மகளிர் குழுக்கள் & உள்ளூர் தலைமைத்துவத்தை வலுப்படுத்துதல்",
            descriptionEn = "Fostering cohesive, self-reliant communities through village volunteer units, women self-help groups training in disaster-resilient livelihoods, and elderly care networks.",
            descriptionTa = "கிராமத் தன்னார்வக் குழுக்களை உருவாக்குதல், பெண்களுக்கு இயற்கை சார்ந்த கைவினை மற்றும் மாற்று வாழ்வாதாரப் பயிற்சிகள் வழங்குதல்.",
            keyInitiativesEn = listOf(
                "Women disaster relief action teams and co-operative kitchens",
                "Eco-handicrafts and palmyra fiber value addition workshops",
                "Village elder welfare checks and emergency transport support",
                "Gram Sabha community advocacy for civic infrastructure"
            ),
            keyInitiativesTa = listOf(
                "அவசரகால சமுதாய சமையல் கூடங்கள் & மகளிர் நிவாரணக் குழுக்கள்",
                "பனை ஓலை கைவினைப் பொருட்கள் மற்றும் வாழ்வாதாரப் பயிற்சி",
                "கிராம முதியோர் நலன் மற்றும் அவசரகால வாகன உதவி",
                "கிராம சபைகளில் குடிநீர், கழிவு மேலாண்மை கோரிக்கைகள் முன்னெடுப்பு"
            ),
            impactTargetEn = "Sustainable livelihood and resilience support for 4,000+ rural households.",
            impactTargetTa = "4,000-க்கும் மேற்பட்ட கிராமக் குடும்பங்களுக்கு வாழ்வாதார வழிகாட்டல்."
        ),
        FocusArea(
            id = "emergency_volunteer",
            iconEmoji = "🚨",
            titleEn = "Emergency Volunteer Response",
            titleTa = "அவசரகால தன்னார்வலர் மீட்புப் படை",
            taglineEn = "Rapid mobilization of trained youth for search, rescue and relief",
            taglineTa = "தேடல், மீட்பு மற்றும் நிவாரணப் பணிகளுக்கான இளைஞர் படை",
            descriptionEn = "A dedicated rapid-response volunteer brigade equipped with life buoys, ropes, first aid kits, chainsaws for tree clearance, and boat rescue coordination during cyclonic floods.",
            descriptionTa = "புயல், வெள்ள காலங்களில் மரங்களை வெட்டி அகற்றல், கயிறுகள், படகுகள் மற்றும் முதலுதவி உபகரணங்களுடன் உடனடி மீட்புப் பணியில் ஈடுபடும் இளைஞர் படை.",
            keyInitiativesEn = listOf(
                "24x7 volunteer alert phone network during IMD weather alerts",
                "Road clearance brigades equipped with electric saws after storms",
                "Dry ration and drinking water kit distribution to marooned hamlets",
                "Close coordination with District Collectorate and Fire & Rescue teams"
            ),
            keyInitiativesTa = listOf(
                "வானிலை எச்சரிக்கையின் போது 24 மணி நேர தன்னார்வலர் அவசர உதவி நெட்வொர்க்",
                "புயலுக்குப் பின் பாதைகளை சீரமைக்க மரங்களை அகற்றும் தன்னார்வலர் குழு",
                "தனிமைப்படுத்தப்பட்ட கிராமங்களுக்கு உலர் உணவு மற்றும் குடிநீர் விநியோகம்",
                "மாவட்ட நிர்வாகம் மற்றும் தீயணைப்புத் துறையுடன் இணைந்து செயல்படுதல்"
            ),
            impactTargetEn = "1,500 certified community first-responders across 9 Delta districts.",
            impactTargetTa = "9 டெல்டா மாவட்டங்களில் 1,500 சான்றிதழ் பெற்ற அவசரகால தன்னார்வலர்கள்."
        ),
        FocusArea(
            id = "waste_management",
            iconEmoji = "♻️",
            titleEn = "Waste Management & Clean Cauvery",
            titleTa = "கழிவு மேலாண்மை & தூய காவேரி இயக்கம்",
            taglineEn = "Protecting rivers and coastal shores from plastic pollution",
            taglineTa = "நதிகள் மற்றும் கடற்கரைகளில் பிளாஸ்டிக் கழிவுகளை அகற்றுதல்",
            descriptionEn = "Tackling single-use plastic dumping in Cauvery channels and coastal beaches. Promoting source segregation, rural composting, and plastic-free pilgrim places like Velankanni and Kumbakonam.",
            descriptionTa = "காவேரி பாசன ஆறுகள் மற்றும் கடலோரப் பகுதிகளில் நெகிழி (பிளாஸ்டிக்) கழிவுகள் கலப்பதைத் தடுத்தல், மக்கும்-மக்காத குப்பைகளைப் பிரித்தல் மற்றும் தூய்மை இயக்கங்கள்.",
            keyInitiativesEn = listOf(
                "Cauvery riverbank cleaning and debris collection drives",
                "Village zero-waste composting and organic waste recycling",
                "Cloth bag alternative campaigns in delta weekly vegetable markets",
                "Coastal beach cleanups at Nagapattinam, Poompuhar, and Velankanni"
            ),
            keyInitiativesTa = listOf(
                "காவேரி ஆற்றங்கரைகள் மற்றும் பாசன வாய்க்கால்கள் தூய்மைப் பணி",
                "கிராமப்புற மக்கும் குப்பையிலிருந்து இயற்கை உரம் தயாரிக்கும் முறை",
                "வாரச் சந்தைகளில் மஞ்சள் பை (துணிப் பை) பயன்பாட்டு விழிப்புணர்வு",
                "பூம்புகார், நாகப்பட்டினம், வேளாங்கண்ணி கடற்கரை தூய்மைப் பணிகள்"
            ),
            impactTargetEn = "Removing 50+ tons of plastic waste annually from delta irrigation channels.",
            impactTargetTa = "ஆண்டுதோறும் 50 டன்களுக்கும் அதிகமான பிளாஸ்டிக் கழிவுகளை அகற்றுதல்."
        )
    )

    val DISASTER_GUIDES = listOf(
        DisasterGuide(
            id = "cyclone",
            titleEn = "Cyclone Preparedness (புயல் தயார்நிலை)",
            titleTa = "புயல் பாதுகாப்பு & தயார்நிலை வழிகாட்டி",
            iconEmoji = "🌀",
            summaryEn = "Crucial steps before, during and after severe cyclonic storms in the Bay of Bengal affecting Delta coast.",
            summaryTa = "வங்கக்கடலில் உருவாகும் புயல்களை எதிர்கொள்ள வேண்டிய அத்தியாவசிய பாதுகாப்பு வழிமுறைகள்.",
            beforeActionEn = listOf(
                "Trim weak tree branches hanging near your house or power cables.",
                "Inspect roof sheets, tie them firmly with wire or sandbags.",
                "Store at least 5 days of non-perishable food, biscuits, and drinking water.",
                "Keep battery torches, power banks, and battery-powered radio fully charged.",
                "Store critical identity papers, land titles, and ration cards in sealed plastic pouches.",
                "Identify the nearest government cyclone relief shelter and emergency evacuation route."
            ),
            beforeActionTa = listOf(
                "வீட்டுக்கு அருகில் மின்கம்பிகள் மீது விழக்கூடிய மரக்கிளைகளை வெட்டி அகற்றவும்.",
                "ஆஸ்பெஸ்டாஸ் மற்றும் தகரக் கூரைகளை கயிறுகள், மணல் மூட்டைகள் கொண்டு பலப்படுத்தவும்.",
                "குறைந்தது 5 நாட்களுக்குத் தேவையான உலர் உணவு, பிஸ்கட் மற்றும் சுத்தமான குடிநீர் சேமிக்கவும்.",
                "டார்ச் லைட், பேட்டரி ரேடியோ மற்றும் பவர் பேங்குகளை முன்கூட்டியே சார்ஜ் செய்து வைக்கவும்.",
                "ஆதார், ரேஷன் கார்டு, நிலப் பத்திரங்கள் போன்ற முக்கிய ஆவணங்களை பிளாஸ்டிக் கவரில் வைக்கவும்.",
                "அருகிலுள்ள புயல் பாதுகாப்பு முகாம் எங்குள்ளது என்பதை முன்கூட்டியே தெரிந்து கொள்ளவும்."
            ),
            duringActionEn = listOf(
                "Stay indoors; never go outside even if winds temporarily subside (eye of the cyclone).",
                "Switch off main electrical power switch and close gas cylinder regulator.",
                "Keep away from glass windows and tin roofs.",
                "Do not spread unverified rumors; follow official District Collector announcements.",
                "If flood water enters, move immediately to higher levels or designated shelters."
            ),
            duringActionTa = listOf(
                "வீட்டிற்குள்ளேயே பாதுகாப்பாக இருக்கவும்; காற்று தற்காலிகமாக நின்றாலும் வெளியே வர வேண்டாம்.",
                "மின்சார மெயின் சுவிட்ச் மற்றும் கேஸ் சிலிண்டர் ரெகுலேட்டரை அணைத்து வைக்கவும்.",
                "கண்ணாடி ஜன்னல்கள் மற்றும் தகரக் கூரைகளின் அருகில் நிற்பதைத் தவிர்க்கவும்.",
                "வதந்திகளை நம்பவோ பரப்பவோ வேண்டாம்; மாவட்ட ஆட்சியர் அறிவிப்புகளை மட்டுமே கவனிக்கவும்.",
                "வெள்ள நீர் வீட்டிற்குள் வந்தால் உடனடியாக உயரமான இடம் அல்லது முகாம்களுக்கு செல்லவும்."
            ),
            afterActionEn = listOf(
                "Beware of snapped electric wires lying in puddles or fallen trees.",
                "Boil all drinking water vigorously for 10 minutes before drinking.",
                "Cooperate with Delta Volunteers Trust and government relief squads.",
                "Watch out for snakes and scorpions in waterlogged houses."
            ),
            afterActionTa = listOf(
                "அறுந்து கிடக்கும் மின்கம்பிகள் மற்றும் விழுந்த மரங்களில் எச்சரிக்கையாக இருக்கவும்.",
                "குடிநீரை குறைந்தது 10 நிமிடங்கள் கொதிக்க வைத்து ஆறிய பின் குடிக்கவும்.",
                "தன்னார்வலர்கள் மற்றும் மீட்புக் குழுவினருக்கு முழு ஒத்துழைப்பு வழங்கவும்.",
                "வெள்ளம் வடிந்த இடங்களில் பாம்புகள், தேள்கள் இருக்க வாய்ப்புள்ளதால் கவனமாக இருக்கவும்."
            ),
            emergencyTipsEn = "Helpline 1077 (District Disaster Control) & 1070 (State Emergency). Keep Delta Volunteers hotline saved.",
            emergencyTipsTa = "அவசர உதவிக்கு 1077 (மாவட்ட கட்டுப்பாட்டு அறை) & 1070. டெல்டா தன்னார்வலர்கள் உதவி எண்களை தொடர்பு கொள்ளவும்."
        ),
        DisasterGuide(
            id = "flood",
            titleEn = "Flood Preparedness (வெள்ளப் பெருக்கு தயார்நிலை)",
            titleTa = "வெள்ளப் பெருக்கு பாதுகாப்பு வழிகாட்டி",
            iconEmoji = "🌊",
            summaryEn = "Safety procedures when Cauvery, Vennar, Kollidam and tributaries experience heavy river discharges.",
            summaryTa = "காவேரி, கொள்ளிடம், வெண்ணாறு உள்ளிட்ட ஆறுகளில் வெள்ளப் பெருக்கு ஏற்படும் போது செய்ய வேண்டியவை.",
            beforeActionEn = listOf(
                "Know the flood contour level of your street and proximity to river bunds.",
                "Prepare an emergency grab-bag with medicines, torch, dry clothes, and baby food.",
                "Untie domestic cattle and livestock; move them to elevated bunds or safe high grounds.",
                "Place sandbags at doorways if your house is in a low-lying zone."
            ),
            beforeActionTa = listOf(
                "உங்கள் குடியிருப்பு பகுதி ஆற்றுப் படுகைக்கு எவ்வளவு அருகில் உள்ளது என்பதை அறியவும்.",
                "மருந்துகள், டார்ச், உடைகள் மற்றும் குழந்தைகள் உணவு அடங்கிய பையை தயாராக வைக்கவும்.",
                "கால்நடைகளை கயிற்றை அவிழ்த்து விட்டு உயரமான மேடான பகுதிகளுக்கு கொண்டு செல்லவும்.",
                "தாழ்வான வீடுகளில் நீர் புகாமல் இருக்க வாசலில் மணல் மூட்டைகளை அடுக்கவும்."
            ),
            duringActionEn = listOf(
                "Never walk, swim or drive through moving flood waters — 15 cm can sweep you off feet.",
                "Keep clear of electrical poles, transformers, and culverts.",
                "Signal for rescue from roof with a bright cloth or whistle if stranded."
            ),
            duringActionTa = listOf(
                "வெள்ள நீரில் இறங்கி நடக்கவோ, நீந்தவோ அல்லது வாகனங்களை ஓட்டவோ வேண்டாம்.",
                "மின் கம்பங்கள், மின் மாற்றிகள் (டிரான்ஸ்பார்மர்) அருகில் செல்லக் கூடாது.",
                "வீட்டில் சிக்கியிருந்தால் கூரை மீது ஏறி சிகப்பு துணி அல்லது விசில் மூலம் மீட்புக்கு சமிக்ஞை செய்யவும்."
            ),
            afterActionEn = listOf(
                "Discard food that came into contact with floodwater.",
                "Disinfect water tanks and wells using bleaching powder before consumption.",
                "Wear rubber boots when clearing mud and debris."
            ),
            afterActionTa = listOf(
                "வெள்ள நீரில் நனைந்த உணவுகளை உண்ணாமல் உடனே அப்புறப்படுத்தவும்.",
                "கிணறு மற்றும் குடிநீர் தொட்டிகளில் குளோரின் / பிளீச்சிங் பவுடர் இட்டு கிருமிநீக்கம் செய்யவும்.",
                "சேற்றை அகற்றும் போது காலணிகள் அல்லது பூட்ஸ் அணிந்து கொள்ளவும்."
            ),
            emergencyTipsEn = "Call 112 or contact the Delta Volunteers Rapid Action Brigade immediately for boat evacuations.",
            emergencyTipsTa = "அவசர மீட்புக்கு 112 அல்லது டெல்டா தன்னார்வலர் மீட்புப் படையை உடனடியாக அழைக்கவும்."
        ),
        DisasterGuide(
            id = "heatwave",
            titleEn = "Heatwave & Summer Resilience (அனல் காற்று & கோடை பாதுகாப்பு)",
            titleTa = "கோடை வெப்ப அலை & அனல் காற்று பாதுகாப்பு",
            iconEmoji = "☀️",
            summaryEn = "Protecting farm workers, elderly and children during peak Agni Nakshatram in the dry delta.",
            summaryTa = "அக்னி நட்சத்திர காலத்தில் முதியோர், குழந்தைகள் மற்றும் விவசாய தொழிலாளர்களைப் பாதுகாத்தல்.",
            beforeActionEn = listOf(
                "Stay hydrated: drink buttermilk (மோர்), tender coconut, ORS, and lemon water.",
                "Wear loose, light-colored cotton clothes and wide-brim hats or cloth towels on head.",
                "Avoid heavy outdoor farm labor between 11:00 AM and 3:30 PM."
            ),
            beforeActionTa = listOf(
                "அதிக அளவு நீர், மோர், இளநீர், எலுமிச்சை சாறு மற்றும் ORS கரைசல் பருகவும்.",
                "வெளிர் நிற பருத்தி ஆடைகளை அணியவும்; தலையில் துண்டு அல்லது தொப்பி அணியவும்.",
                "முற்பகல் 11:00 மணி முதல் பிற்பகல் 3:30 மணி வரை கடுமையான வெயில் வேலைகளைத் தவிர்க்கவும்."
            ),
            duringActionEn = listOf(
                "Recognize heatstroke symptoms: dizziness, nausea, high body temperature without sweating.",
                "Move person to shade immediately, fan them, apply wet cloth packs to neck and armpits."
            ),
            duringActionTa = listOf(
                "வெப்ப மயக்கம் (சன் ஸ்ட்ரோக்) அறிகுறிகள்: தலைசுற்றல், மயக்கம், வாந்தி, அதிக உடல் சூடு.",
                "உடனடியாக நிழலான இடத்திற்கு கொண்டு சென்று குளிர்ந்த ஈரத்துணியால் உடலைத் துடைக்கவும்."
            ),
            afterActionEn = listOf(
                "Keep water bowls outside homes for birds and stray animals.",
                "Ensure community water pots (தண்ணீர் பந்தல்) are refilled with clean safe water."
            ),
            afterActionTa = listOf(
                "பறவைகள் மற்றும் விலங்குகளுக்காக வீட்டு வாசலில் தண்ணீர் பாத்திரம் வைக்கவும்.",
                "கிராம நீர் பந்தல்களில் சுத்தமான குடிநீர் இருப்பதை உறுதி செய்யவும்."
            ),
            emergencyTipsEn = "Dial 108 for emergency ambulance in case of severe heatstroke or loss of consciousness.",
            emergencyTipsTa = "மயக்கமடைந்தால் தாமதிக்காமல் 108 ஆம்புலன்ஸை அழைக்கவும்."
        ),
        DisasterGuide(
            id = "first_aid",
            titleEn = "Delta First Aid Essentials (முக்கிய முதலுதவி குறிப்புகள்)",
            titleTa = "அத்தியாவசிய முதலுதவி & பாம்புக்கடி வழிகாட்டி",
            iconEmoji = "🩹",
            summaryEn = "Crucial life-saving techniques for wound bleeding, fractures, drowning, and field snakebites.",
            summaryTa = "ரத்தப்போக்கு, நீரில் மூழ்குதல், எலும்பு முறிவு மற்றும் வயல்வெளி பாம்புக்கடி முதலுதவி.",
            beforeActionEn = listOf(
                "Always keep a family First Aid Box with Dettol, sterile gauze, bandages, ORS, and paracetamol.",
                "Learn the location of the nearest 24-hr Primary Health Centre (PHC) with Anti-Snake Venom (ASV)."
            ),
            beforeActionTa = listOf(
                "வீட்டில் முதலுதவி பெட்டி, பஞ்சு, பேண்டேஜ், கிருமிநாசினி மற்றும் வலி நிவாரணிகள் வைத்திருக்கவும்.",
                "பாம்புக்கடி மாற்று மருந்து (ASV) உள்ள அரசு ஆரம்ப சுகாதார நிலையம் எங்குள்ளது என அறியவும்."
            ),
            duringActionEn = listOf(
                "For severe bleeding: Apply direct continuous pressure with clean cloth for 10 minutes.",
                "For Snakebite: DO NOT cut, suck or tie tight tourniquet! Keep patient calm, immobilize bitten limb with a splint, and rush to nearest Government Hospital immediately.",
                "For Drowning: Clear mouth obstruction, perform CPR (30 chest compressions : 2 rescue breaths) until help arrives."
            ),
            duringActionTa = listOf(
                "கடுமையான ரத்தப்போக்குக்கு: சுத்தமான துணியை வைத்து அழுத்தமாக 10 நிமிடங்கள் அழுத்திப் பிடிக்கவும்.",
                "பாம்புக்கடிக்கு: கத்தியால் கீறவோ, வாயால் உறிஞ்சவோ, கயிற்றால் இறுக்கமாக கட்டவோ கூடாது! நோயாளியை பதற்றமின்றி படுக்க வைத்து உடனே அரசு மருத்துவமனைக்கு அழைத்துச் செல்லவும்.",
                "நீரில் மூழ்கியவருக்கு: வாயில் சேறு இருந்தால் நீக்கி, CPR மார்பு அழுத்தப் பயிற்சி அளிக்கவும்."
            ),
            afterActionEn = listOf(
                "Never administer oral liquids to an unconscious individual.",
                "Delta Volunteers conduct monthly first-aid training camps across all taluks."
            ),
            afterActionTa = listOf(
                "மயக்கத்தில் உள்ளவருக்கு ஒருபோதும் வாயில் நீர் அல்லது பானங்களை ஊற்றக் கூடாது.",
                "டெல்டா தன்னார்வலர்கள் நடத்தும் மாதாந்திர முதலுதவிப் பயிற்சிகளில் பங்குபெறவும்."
            ),
            emergencyTipsEn = "Emergency Ambulance: 108. Government Medical Helpdesk: 104.",
            emergencyTipsTa = "ஆம்புலன்ஸ் அவசர எண்: 108. அரசு மருத்துவ ஆலோசனை: 104."
        ),
        DisasterGuide(
            id = "vulnerable_care",
            titleEn = "Special Care Protocols (முதியோர், குழந்தைகள் & மாற்றுத்திறனாளிகள் பாதுகாப்பு)",
            titleTa = "சிறப்பு கவனப் பாதுகாப்பு நெறிமுறைகள்",
            iconEmoji = "🤝",
            summaryEn = "Dedicated community safeguards for infants, pregnant mothers, elders, and differently-abled neighbors.",
            summaryTa = "கர்ப்பிணிப் பெண்கள், குழந்தைகள், முதியவர்கள் மற்றும் மாற்றுத்திறனாளிகளுக்கான பேரிடர் கால பாதுகாப்பு.",
            beforeActionEn = listOf(
                "Prepare a village census of bedridden persons, pregnant women, and dialyis patients.",
                "Evacuate expectant mothers near delivery date to Taluk Hospital 48 hours before cyclone landfall.",
                "Keep backup mobility aids (crutches, wheelchairs, adult diapers) packed in dry bags."
            ),
            beforeActionTa = listOf(
                "கிராமத்தில் உள்ள படுக்கை நோயாளிகள், கர்ப்பிணிப் பெண்கள் பற்றிய பட்டியலை முன்கூட்டியே தயார் செய்தல்.",
                "பிரசவ தேதி நெருங்கும் தாய்மார்களை புயலுக்கு 2 நாட்கள் முன்னதாகவே அரசு மருத்துவமனைக்கு மாற்றவும்.",
                "மாற்றுத்திறனாளிகளுக்கான சக்கர நாற்காலி, ஊன்றுகோல் போன்றவற்றை தயாராக வைக்கவும்."
            ),
            duringActionEn = listOf(
                "Assign 2 designated volunteer buddies for every elderly or differently-abled resident.",
                "Ensure infants receive boiled water and warm dry clothing to prevent hypothermia.",
                "Keep a battery-operated torch and personal whistle with visually impaired persons."
            ),
            duringActionTa = listOf(
                "ஒவ்வொரு முதியவர் மற்றும் மாற்றுத்திறனாளிக்கும் 2 தன்னார்வலர்களை வழிகாட்டிகளாக நியமிக்கவும்.",
                "கைக்குழந்தைகளுக்கு கொதிக்க வைத்த நீர் மற்றும் வெதுவெதுப்பான ஆடைகளை உறுதிப்படுத்தவும்.",
                "பார்வைக் குறைபாடு உள்ளவர்களுக்கு விசில் மற்றும் டார்ச் லைட் வழங்கி உடனிருக்கவும்."
            ),
            afterActionEn = listOf(
                "Provide priority medical screening for chronically ill persons post-disaster.",
                "Offer psychosocial emotional support and child-friendly spaces in relief centers."
            ),
            afterActionTa = listOf(
                "முகாம்களில் நாள்பட்ட நோயாளிகளுக்கு மருத்துவ பரிசோதனைக்கு முன்னுரிமை அளிக்கவும்.",
                "குழந்தைகளுக்கு அச்சத்தைப் போக்க விளையாட்டு மற்றும் விழிப்புணர்வு அரவணைப்பு வழங்கவும்."
            ),
            emergencyTipsEn = "Delta Volunteers Elderly & Vulnerable Help Desk: Contact via app or toll-free.",
            emergencyTipsTa = "டெல்டா சிறப்பு உதவி மையம் மூலம் முன்னுரிமை மீட்பு வாகன வசதி பெறலாம்."
        )
    )

    val EMERGENCY_KIT_ITEMS = listOf(
        EmergencyKitItem("kit_1", "High-beam LED Torch with Extra Batteries", "அதிக வெளிச்சம் தரும் டார்ச் லைட் & பேட்டரிகள்", "Essential for power cuts and night rescues"),
        EmergencyKitItem("kit_2", "Sealed 5L Drinking Water Jugs", "5 லிட்டர் சுத்தமான பாதுகாப்பான குடிநீர்", "At least 3-5 liters per person"),
        EmergencyKitItem("kit_3", "Ready-to-eat Dry Rations (Biscuits, Flattened Rice / Aval, Jaggery)", "உலர் உணவுப் பொருட்கள் (அவல், பொரி, வெல்லம், பிஸ்கட்)", "Non-cooking high energy foods"),
        EmergencyKitItem("kit_4", "Personal Identity Documents in Waterproof Ziplock", "பிளாஸ்டிக் கவரில் ஆதார், ரேஷன் கார்டு, பட்டா ஆவணங்கள்", "Aadhaar, ration card, voter ID, bank passbook"),
        EmergencyKitItem("kit_5", "First Aid Kit & Chronic Prescription Medicines", "முதலுதவிப் பெட்டி & தொடர்ந்து சாப்பிடும் மருந்துகள்", "BP, sugar, asthma medications for 10 days"),
        EmergencyKitItem("kit_6", "Power Bank (Fully Charged) & Charging Cables", "முழுமையாக சார்ஜ் செய்யப்பட்ட பவர் பேங்க்", "To keep mobile operational for distress calls"),
        EmergencyKitItem("kit_7", "Emergency Whistle", "அவசரகால உதவிக்கான விசில்", "Helps search and rescue volunteers pinpoint location"),
        EmergencyKitItem("kit_8", "Water Purification Tablets (Chlorine / Halazone)", "குடிநீர் சுத்திகரிக்கும் குளோரின் மாத்திரைகள்", "Purifies cloudy rainwater into safe drinking water"),
        EmergencyKitItem("kit_9", "Matchbox / Lighter & Wax Candles in Waterproof box", "தீப்பெட்டி & மெழுகுவர்த்திகள்", "For lighting and warmth"),
        EmergencyKitItem("kit_10", "Extra Set of Dry Clothes & Raincoats/Plastic Sheets", "உலர் ஆடைகள் மற்றும் ரெயின்கோட்", "Prevents hypothermia and pneumonia in children")
    )

    val WATER_TOPICS = listOf(
        WaterTopic(
            id = "safe_standards",
            titleEn = "Safe Drinking Water Standards & Testing",
            titleTa = "பாதுகாப்பான குடிநீர் தர அளவீடுகள் & பரிசோதனை",
            iconEmoji = "🧪",
            summaryEn = "Understanding key parameters like TDS, pH, hardness, and bacteriological safety in Delta.",
            summaryTa = "குடிநீரின் TDS, pH அளவு, கடினத்தன்மை மற்றும் நுண்ணுயிர் பாதுகாப்பு பற்றிய புரிதல்.",
            keyPointsEn = listOf(
                "Ideal TDS for drinking: 50 – 150 mg/L (acceptable up to 300 mg/L).",
                "TDS above 500 mg/L tastes salty and can strain kidneys over prolonged use.",
                "pH level should be between 6.5 and 8.5 for healthy consumption.",
                "Cloudy or foul-smelling tap water indicates microbial contamination or pipeline breaches."
            ),
            keyPointsTa = listOf(
                "சிறந்த குடிநீர் TDS அளவு: 50 முதல் 150 mg/L (300 mg/L வரை ஏற்றுக்கொள்ளத்தக்கது).",
                "500 mg/L-க்கு மேல் TDS இருந்தால் நீர் உப்பாக இருக்கும்; நீண்டகால பயன்பாட்டில் உடல்நலக் குறைவு ஏற்படலாம்.",
                "குடிநீரின் கார-அமிலத்தன்மை (pH) 6.5 முதல் 8.5 வரை இருக்க வேண்டும்.",
                "கலங்கலான அல்லது துர்நாற்றம் வீசும் குழாய் நீரில் சாக்கடை கலப்பு அல்லது பாக்டீரியா இருக்க வாய்ப்புள்ளது."
            ),
            practicalTipEn = "Delta Volunteers Trust conducts free TDS meter tests. Request a testing camp for your village through the Report Need section!",
            practicalTipTa = "டெல்டா வாலண்டியர்ஸ் டிரஸ்ட் உங்கள் கிராமத்தில் இலவச குடிநீர் பரிசோதனை முகாம் நடத்த கோரிக்கை அனுப்பலாம்."
        ),
        WaterTopic(
            id = "household_safety",
            titleEn = "Household Water Safety & Purification",
            titleTa = "வீட்டு குடிநீர் பாதுகாப்பு & சுத்திகரிக்கும் முறைகள்",
            iconEmoji = "🚰",
            summaryEn = "Simple, zero-cost and low-cost methods to ensure your family drinks 100% germ-free water.",
            summaryTa = "வீட்டிலேயே எளிதாக குடிநீரை கிருமிநீக்கம் செய்யும் பாரம்பரிய மற்றும் நவீன வழிகள்.",
            keyPointsEn = listOf(
                "Rolling boil water for at least 3 to 5 minutes to kill all bacteria, cysts and viruses.",
                "Store purified water in clean, narrow-neck earthenware pots (மண்பானை) with a lid and ladle.",
                "Never dip bare hands into drinking water pots; use a long-handled tap or ladle.",
                "Clean domestic overhead and ground-level sumps at least once every 3 months."
            ),
            keyPointsTa = listOf(
                "நீரை குறைந்தது 3 முதல் 5 நிமிடங்கள் நன்கு கொதிக்க வைத்தால் 100% பாக்டீரியாக்கள் அழியும்.",
                "சுத்தமான மண்பானையில் மூடி போட்டு குடிநீரை சேமித்து வைக்கவும்.",
                "குடிநீருக்குள் கைகளை விட்டு எடுக்காமல், கரண்டி அல்லது குழாய் மூலம் மட்டுமே நீரை எடுக்கவும்.",
                "வீட்டு குடிநீர் தொட்டிகளை 3 மாதங்களுக்கு ஒருமுறை பிளீச்சிங் பவுடர் கொண்டு சுத்தம் செய்யவும்."
            ),
            practicalTipEn = "Add Vetiver (வெட்டிவேர்) roots or Tulsi leaves to drinking earthen pots for natural cooling and freshness.",
            practicalTipTa = "மண்பானை குடிநீரில் வெட்டிவேர் அல்லது துளசி இலைகளை இட்டு வைப்பது உடலுக்கு குளிர்ச்சியும் நன்மையும் தரும்."
        ),
        WaterTopic(
            id = "rainwater_harvesting",
            titleEn = "Rainwater Harvesting (மழைநீர் சேகரிப்பு)",
            titleTa = "மழைநீர் சேகரிப்பு & நிலத்தடி நீர் செறிவூட்டல்",
            iconEmoji = "🌧️",
            summaryEn = "Recharging Cauvery delta aquifers and countering coastal salinity through rooftop harvesting.",
            summaryTa = "மழைநீரை பூமிக்குள் செலுத்தி கடல்நீர் உட்புகுதலை தடுத்து நன்னீராக மாற்றுதல்.",
            keyPointsEn = listOf(
                "Filter rooftop rainwater through a simple sand, gravel and charcoal filter bed.",
                "Direct overflow into an open recharge well or borewell injection recharge pit.",
                "Just 1,000 sq.ft of roof can capture 80,000 to 100,000 liters of pristine rainwater annually in Tamil Nadu.",
                "Helps reverse saltwater intrusion in coastal wells in Nagapattinam, Mayiladuthurai and Cuddalore."
            ),
            keyPointsTa = listOf(
                "மொட்டை மாடி மழைநீரை மணல், ஜல்லி மற்றும் கரித்தூள் வடிகட்டி மூலம் வடிகட்டவும்.",
                "வடிகட்டிய நீரை உறிஞ்சு குழிகள் மூலம் நிலத்தடிக்குள் செலுத்தி நிலத்தடி நீர்மட்டத்தை உயர்த்தலாம்.",
                "1,000 சதுர அடி கூரையில் ஆண்டுக்கு 80,000 முதல் 1 லட்சம் லிட்டர் வரை மழைநீரை சேமிக்க முடியும்.",
                "கடலோர பகுதிகளில் கிணற்று நீர் உப்பாவதை தடுக்க மழைநீர் சேகரிப்பே ஒரே தீர்வு."
            ),
            practicalTipEn = "Delta Volunteers provides free engineering guidance for schools and village temples to install rainwater recharge pits.",
            practicalTipTa = "பள்ளிகள், கோவில்களில் மழைநீர் சேகரிப்பு கட்டமைப்புகளை உருவாக்க எங்கள் பொறியியல் தன்னார்வலர்கள் வழிகாட்டுகின்றனர்."
        ),
        WaterTopic(
            id = "waterbody_restoration",
            titleEn = "Waterbody & Oorani Revitalization",
            titleTa = "ஊரணிகள் & கிராமக் குளங்கள் தூர்வாருதல்",
            iconEmoji = "🏞️",
            summaryEn = "Community science behind de-silting, inlet canal clearance, and native bund stabilization.",
            summaryTa = "கிராமத்தின் பாரம்பரிய நீர் ஆதாரங்களை மீட்டெடுத்து கோடை காலத்திலும் வற்றாமல் பாதுகாத்தல்.",
            keyPointsEn = listOf(
                "Clear wild thorny shrubs (சீமைக் கருவேலம்) and water hyacinth (ஆகாயத் தாமரை) that choke water flow.",
                "Desilt accumulated silt to increase pond holding capacity and use rich silt in agricultural fields.",
                "Establish three-tier native plantation on bunds: Vetiver at water edge, Bamboo in middle, Palmyrah on top.",
                "Construct step bathing ghats and separate cattle washing zones to keep central water clean."
            ),
            keyPointsTa = listOf(
                "நீர்நிலைகளை ஆக்கிரமிக்கும் சீமைக் கருவேலம் மற்றும் ஆகாயத் தாமரையை அடியோடு அகற்றவும்.",
                "வண்டல் மண்ணை தூர்வாரி குளத்தின் ஆழத்தை அதிகப்படுத்துவதுடன், அந்த வளமான மண்ணை விவசாயத்திற்கு பயன்படுத்தலாம்.",
                "குளக்கரைகளில் வெட்டிவேர், மூங்கில் மற்றும் பனை மரங்களை நட்டு கரைகளை பலப்படுத்தவும்.",
                "குளிக்கும் இடம், கால்நடைகள் குடிக்கும் இடத்தை தனித்தனியாக அமைத்து நீர் மாசுபடுவதைத் தடுக்கவும்."
            ),
            practicalTipEn = "Report any silted village pond or encroached inlet canal on our app to initiate community restoration!",
            practicalTipTa = "உங்கள் ஊரில் தூர்வாரப்பட வேண்டிய குளம் அல்லது ஆக்கிரமிக்கப்பட்ட கால்வாய்களை செயலி மூலம் தெரிவியுங்கள்!"
        )
    )

    val PROJECTS = listOf(
        ProjectItem(
            id = "prj_1",
            titleEn = "Cauvery Delta Mangrove Shield Project",
            titleTa = "டெல்டா கடலோர அலையாத்திக் காடுகள் பாதுகாப்பு இயக்கம்",
            location = "Muthupet & Pichavaram Coastal Belt",
            district = "Tiruvarur / Cuddalore",
            status = "Ongoing",
            objectiveEn = "Planting 50,000 mangrove propagules to create a natural bio-shield against Bay of Bengal cyclones.",
            objectiveTa = "கடலோர புயல் மற்றும் பேரலைகளில் இருந்து கிராமங்களை காக்க 50,000 சதுப்புநில அலையாத்திக் கன்றுகள் நடுதல்.",
            activitiesEn = listOf(
                "Establishing community mangrove nursery in Muthupet lagoon",
                "Restoring tidal flushed fish-bone channels",
                "Training 300 fisherfolk youth as coastal bio-guardians"
            ),
            activitiesTa = listOf(
                "முத்துப்பேட்டை காயலில் சமுதாய சதுப்புநில நாற்றுப்பண்ணை அமைத்தல்",
                "அலைநீர் தடையின்றி பாயும் வாய்க்கால்களை சீரமைத்தல்",
                "300 மீனவ இளைஞர்களுக்கு கடலோர பாதுகாப்பு தன்னார்வலர் பயிற்சி"
            ),
            beneficiaries = "18 Coastal Fishing Hamlets (14,000+ residents)",
            impactMetric = "32 Hectares Mangrove Canopy Restored",
            iconEmoji = "🌳"
        ),
        ProjectItem(
            id = "prj_2",
            titleEn = "Jal Suraksha: Safe School Water Mission",
            titleTa = "ஜல் சுரக்ஷா: அரசுப் பள்ளி பாதுகாப்பான குடிநீர் திட்டம்",
            location = "Rural Government Schools",
            district = "Nagapattinam & Mayiladuthurai",
            status = "Ongoing",
            objectiveEn = "Ensuring 100% bacteria-free drinking water and functional handwash stations across 50 rural government schools.",
            objectiveTa = "50 கிராமப்புற அரசுப் பள்ளிகளில் பாதுகாப்பான குடிநீர் மற்றும் கை கழுவும் வசதிகளை உறுதி செய்தல்.",
            activitiesEn = listOf(
                "Comprehensive chemical & biological water testing",
                "Installing multi-stage sediment and UV/UF water filtration systems",
                "Student Eco-Club water quality monitoring training"
            ),
            activitiesTa = listOf(
                "பள்ளி குடிநீரின் தரம் மற்றும் நச்சுத்தன்மை பரிசோதனை",
                "பள்ளிகளில் தரமான குடிநீர் சுத்திகரிப்பு இயந்திரங்கள் நிறுவுதல்",
                "மாணவர்களுக்கு நீர் விழிப்புணர்வு மற்றும் பரிசோதனை பயிற்சி"
            ),
            beneficiaries = "9,500+ Rural School Children",
            impactMetric = "38 Schools Equipped with Safe RO/UV Units",
            iconEmoji = "💧"
        ),
        ProjectItem(
            id = "prj_3",
            titleEn = "Namma Oorani: Village Pond Revitalization",
            titleTa = "நம்ம ஊரணி: கிராமக் குளங்கள் புனரமைப்பு திட்டம்",
            location = "Kumbakonam & Papanasam Blocks",
            district = "Thanjavur",
            status = "Completed",
            objectiveEn = "Community-driven desilting and bund reinforcement of 25 historical irrigation and village drinking water tanks.",
            objectiveTa = "25 பழமையான கிராம ஊரணிகளை தூர்வாரி, கரைகளை பலப்படுத்தி நீர் கொள்ளளவை அதிகரித்தல்.",
            activitiesEn = listOf(
                "Excavating 45,000 cubic meters of fertile silt for farmers",
                "Sowing 12,000 Palmyrah seeds on reservoir banks",
                "Reconnecting historical Cauvery feeder canals"
            ),
            activitiesTa = listOf(
                "45,000 கனமீட்டர் வண்டல் மண் தூர்வாரப்பட்டு விவசாயிகளுக்கு வழங்கப்பட்டது",
                "குளக்கரைகளில் 12,000 பனை விதைகள் நடப்பட்டன",
                "காவேரி வரத்துக் கால்வாய்கள் தூர்வாரப்பட்டு இணைக்கப்பட்டன"
            ),
            beneficiaries = "22 Delta Villages (28,000+ population)",
            impactMetric = "180 Million Liters Rainwater Storage Added",
            iconEmoji = "🏞️"
        ),
        ProjectItem(
            id = "prj_4",
            titleEn = "Cyclone Disaster Rapid Response Brigade",
            titleTa = "புயல் பேரிடர் அவசரகால மீட்புப் படை",
            location = "Delta Coastal & Lowland Taluks",
            district = "All Delta Districts",
            status = "Ongoing",
            objectiveEn = "Equipping and training 1,200 grassroots volunteers for zero-casualty emergency evacuation and post-storm relief.",
            objectiveTa = "புயல் காலங்களில் உடனடியாக மீட்புப் பணியில் ஈடுபட 1,200 தன்னார்வலர்களுக்கு நவீன மீட்புக் கருவிகள் & பயிற்சி.",
            activitiesEn = listOf(
                "Conducting coastal storm surge evacuation mock drills",
                "Procuring chainsaws, inflatable rescue boats, and high-frequency walkie-talkies",
                "Stockpiling 5,000 emergency family relief ration kits"
            ),
            activitiesTa = listOf(
                "கடலோர கிராமங்களில் மாதிரி வெளியேற்ற ஒத்திகை நடத்துதல்",
                "மரம் அறுக்கும் இயந்திரங்கள், மீட்புப் படகுகள் மற்றும் வாக்கி-டாக்கி தயார்நிலை",
                "5,000 குடும்பங்களுக்கான அவசரகால உலர் உணவுப் பொட்டலங்கள் தயார் செய்தல்"
            ),
            beneficiaries = "50,000+ Potential Flood-Vulnerable Residents",
            impactMetric = "1,200 Certified Volunteer First Responders",
            iconEmoji = "🚨"
        ),
        ProjectItem(
            id = "prj_5",
            titleEn = "Green Delta: 100,000 Native Tree Mission",
            titleTa = "பசுமை டெல்டா: 1 லட்சம் நாட்டு மரங்கள் நடுதல் இயக்கம்",
            location = "Roadsides, Canal Bunds, School Campuses",
            district = "Pudukkottai & Thanjavur",
            status = "Ongoing",
            objectiveEn = "Restoring native tree species like Neem, Pungai, Marudham, and Illuppai to combat scorching summer heatwaves.",
            objectiveTa = "வேம்பு, புங்கை, மருதம், இலுப்பை போன்ற பாரம்பரிய மரங்களை நட்டு பசுமைப் போர்வையை உருவாக்குதல்.",
            activitiesEn = listOf(
                "Distribution of 40,000 free saplings to smallholder farmers",
                "Drip irrigation setup through recycled plastic bottles",
                "Geotagging planted saplings for 3-year survival tracking"
            ),
            activitiesTa = listOf(
                "சிறு, குறு விவசாயிகளுக்கு 40,000 இலவச மரக்கன்றுகள் விநியோகம்",
                "சொட்டு நீர் பாசன முறையில் கன்றுகள் பராமரிப்பு",
                "மரக்கன்றுகளை ஜிபிஎஸ் மூலம் தொடர்ந்து கண்காணித்தல்"
            ),
            beneficiaries = "120 Rural Habitations",
            impactMetric = "64,000+ Saplings Actively Growing (84% survival)",
            iconEmoji = "🌱"
        ),
        ProjectItem(
            id = "prj_6",
            titleEn = "Vidiyal: Child Resilience & Eco-Clubs",
            titleTa = "விடியல்: கிராம மாணவர் சூழலியல் மன்றங்கள்",
            location = "Government Middle & High Schools",
            district = "Tiruvarur",
            status = "Completed",
            objectiveEn = "Instilling disaster preparedness, seedball making, and climate consciousness in next-generation leaders.",
            objectiveTa = "மாணவர்களுக்கு பேரிடர் தற்காப்பு, விதைப்பந்து தயாரித்தல் மற்றும் சுற்றுச்சூழல் விழிப்புணர்வு ஏற்படுத்துதல்.",
            activitiesEn = listOf(
                "Organizing 30 school nature camps and bird watching in delta wetlands",
                "Distributing 25,000 seedballs prepared by students",
                "School emergency safety clubs establishment"
            ),
            activitiesTa = listOf(
                "30 பள்ளிகளில் இயற்கை விழிப்புணர்வு மற்றும் பறவைகள் நோக்கும் முகாம்கள்",
                "மாணவர்களால் தயாரிக்கப்பட்ட 25,000 விதைப்பந்துகள் தூவுதல்",
                "பள்ளி அவசரகால முதலுதவி குழுக்கள் அமைத்தல்"
            ),
            beneficiaries = "4,200 Students across 25 Schools",
            impactMetric = "25 Active Eco-Clubs Created",
            iconEmoji = "🏫"
        )
    )

    val EVENTS = listOf(
        EventItem(
            id = "evt_1",
            titleEn = "Delta Volunteer Induction & Emergency Drill",
            titleTa = "டெல்டா தன்னார்வலர் அறிமுகக் கூட்டம் & பேரிடர் ஒத்திகை",
            category = "Volunteer Training",
            dateStr = "Saturday, Oct 10, 2026",
            timeStr = "09:30 AM - 01:30 PM",
            location = "Raja Mirasudar Hall, Court Road",
            district = "Thanjavur",
            descriptionEn = "Comprehensive orientation for newly registered volunteers: basic search & rescue, CPR demonstrations, and communication protocols during monsoon emergencies.",
            descriptionTa = "புதிய தன்னார்வலர்களுக்கான அறிமுகக் கூட்டம்: தேடல் மற்றும் மீட்புப் பயிற்சி, CPR முதலுதவி விளக்கம் மற்றும் தகவல் தொடர்பு நெறிமுறைகள்.",
            targetAudience = "All registered volunteers and youth aged 18+",
            iconEmoji = "👥"
        ),
        EventItem(
            id = "evt_2",
            titleEn = "Muthupet Coastal Mangrove Plantation Drive",
            titleTa = "முத்துப்பேட்டை சதுப்புநில அலையாத்தி கன்றுகள் நடும் இயக்கம்",
            category = "Environmental Drive",
            dateStr = "Sunday, Oct 18, 2026",
            timeStr = "06:30 AM - 11:30 AM",
            location = "Muthupet Lagoon Eco-Station",
            district = "Tiruvarur",
            descriptionEn = "Field trip to plant 2,000 Avicennia mangrove propagules along tidal channels. Transport, life jackets and breakfast provided for volunteers.",
            descriptionTa = "2,000 அலையாத்திக் கன்றுகளை நடும் களம். தன்னார்வலர்களுக்கு போக்குவரத்து, பாதுகாப்பு உபகரணங்கள் மற்றும் காலை உணவு வழங்கப்படும்.",
            targetAudience = "Environmental volunteers & college students",
            iconEmoji = "🌳"
        ),
        EventItem(
            id = "evt_3",
            titleEn = "Community Water Quality & TDS Testing Workshop",
            titleTa = "கிராம குடிநீர் தரம் & TDS பரிசோதனை பயிலரங்கம்",
            category = "Disaster Preparedness",
            dateStr = "Wednesday, Oct 28, 2026",
            timeStr = "10:00 AM - 02:00 PM",
            location = "Government ITI Campus, Vedaranyam",
            district = "Nagapattinam",
            descriptionEn = "Hands-on training using digital TDS meters, pH strips, and chlorine testing kits. Volunteers receive a free field testing kit to inspect their village drinking water.",
            descriptionTa = "டிஜிட்டல் TDS மீட்டர்கள் மற்றும் குளோரின் பரிசோதனை உபகரணங்களைப் பயன்படுத்தி குடிநீரின் தரத்தை பரிசோதிக்க பயிற்சி.",
            targetAudience = "Panchayat representatives, teachers, youth",
            iconEmoji = "💧"
        ),
        EventItem(
            id = "evt_4",
            titleEn = "School Safety & Earthquake/Cyclone Mock Drill",
            titleTa = "பள்ளி பேரிடர் தற்காப்பு ஒத்திகை முகாம்",
            category = "School Program",
            dateStr = "Friday, Nov 06, 2026",
            timeStr = "02:00 PM - 04:30 PM",
            location = "Govt. Girls Higher Secondary School",
            district = "Mayiladuthurai",
            descriptionEn = "Demonstrating Duck-Cover-Hold, rapid room evacuation, and stretcher improvisation using school supplies in front of 800 students.",
            descriptionTa = "மாணவிகளுக்கு பேரிடர் காலத்தில் பாதுகாப்பாக வெளியேறும் ஒத்திகை மற்றும் முதலுதவி செயல்முறை விளக்கம்.",
            targetAudience = "School teachers and high school students",
            iconEmoji = "🏫"
        ),
        EventItem(
            id = "evt_5",
            titleEn = "Palmyrah (பனை) Seed Sowing on Cauvery Bunds",
            titleTa = "காவேரி கரைகளில் 10,000 பனை விதைகள் நடும் திருவிழா",
            category = "Environmental Drive",
            dateStr = "Sunday, Nov 15, 2026",
            timeStr = "07:00 AM - 12:00 PM",
            location = "Cauvery River Bund, Thiruvaiyaru",
            district = "Thanjavur",
            descriptionEn = "Massive community campaign to plant 10,000 palm seeds along vulnerable river embankments to stop monsoon soil erosion.",
            descriptionTa = "மண் அரிப்பைத் தடுத்து நதிக்கரைகளை பலப்படுத்த 10,000 பனை விதைகள் நடும் சமுதாய பெருவிழா.",
            targetAudience = "Farmers, volunteers, nature lovers",
            iconEmoji = "🌱"
        )
    )

    val GALLERY_ITEMS = listOf(
        GalleryItem("gal_1", "Mangrove Restoration at Muthupet", "முத்துப்பேட்டை சதுப்புநில மீட்டெடுப்பு", "Environment", "Muthupet, Tiruvarur", "Volunteers wading through tidal waters to plant native mangrove seedlings.", "தன்னார்வலர்கள் சதுப்புநிலக் கன்றுகளை நடும் காட்சி.", "🌳", "12,000+ Saplings"),
        GalleryItem("gal_2", "Emergency Flood Relief Distribution", "வெள்ள நிவாரணப் பொருட்கள் விநியோகம்", "Disaster Response", "Sirkazhi, Mayiladuthurai", "Supplying safe drinking water cans and dry rations to waterlogged hamlets.", "வெள்ளத்தில் பாதிக்கப்பட்ட மக்களுக்கு குடிநீர் மற்றும் உணவு விநியோகம்.", "🚨", "8,500 Kits Distributed"),
        GalleryItem("gal_3", "Village Water TDS Testing Camp", "கிராம குடிநீர் TDS பரிசோதனை முகாம்", "Water Projects", "Thirukkuvalai, Nagapattinam", "Field testing salinity and hardness in village borewell samples.", "கிராம கிணற்று நீரின் உப்புத்தன்மை மற்றும் TDS பரிசோதனை.", "💧", "140 Water Sources Tested"),
        GalleryItem("gal_4", "School Disaster Safety Demonstration", "பள்ளி பேரிடர் தற்காப்பு செயல்விளக்கம்", "Education", "Kumbakonam, Thanjavur", "Teaching school children first-aid, stretcher carrying, and cyclone survival.", "மாணவர்களுக்கு முதலுதவி மற்றும் தற்காப்பு பயிற்சி.", "🏫", "65 Schools Covered"),
        GalleryItem("gal_5", "Tree Planting Along Cauvery Embankments", "காவேரி கரைகளில் மரக்கன்றுகள் நடுதல்", "Volunteer Activities", "Thiruvaiyaru, Thanjavur", "Over 200 college volunteers planting native fruit trees and palmyra seeds.", "கல்லூரி மாணவர்கள் பங்கேற்ற மரக்கன்றுகள் நடும் இயக்கம்.", "🌱", "25 km Bund Planted"),
        GalleryItem("gal_6", "Community Kitchen During Heavy Rains", "மழைக்கால சமுதாய சமையல் கூடம்", "Community", "Cuddalore Coastal Block", "Hot meals prepared and distributed to elderly citizens sheltered in schools.", "முகாம்களில் தங்கியுள்ள முதியவர்களுக்கு சூடான சத்தான உணவு தயாரிப்பு.", "👥", "3,200 Meals Served Daily")
    )

    val INITIAL_NEWS = listOf(
        NewsEntity(
            titleEn = "Northeast Monsoon Volunteer Preparedness Alert",
            titleTa = "வடகிழக்கு பருவமழை தன்னார்வலர் தயார்நிலை எச்சரிக்கை",
            contentEn = "With the onset of monsoon rains across the Delta region, all Delta Volunteers Trust coordinators in Thanjavur, Nagapattinam, Mayiladuthurai and Tiruvarur are placed on alert. Check rescue gear and emergency kits.",
            contentTa = "டெல்டா மாவட்டங்களில் வடகிழக்கு பருவமழை தீவிரமடைவதையொட்டி, அனைத்து தன்னார்வலர் ஒருங்கிணைப்பாளர்களும் தயார்நிலையில் இருக்குமாறு கேட்டுக்கொள்ளப்படுகிறார்கள். மீட்புக் கருவிகளை சரிபார்க்கவும்.",
            category = "Alert",
            dateStr = "Sep 22, 2026",
            isUrgent = true
        ),
        NewsEntity(
            titleEn = "New Water Testing Laboratory Setup in Thanjavur",
            titleTa = "தஞ்சாவூரில் புதிய குடிநீர் பரிசோதனை மையம் தொடக்கம்",
            contentEn = "Delta Volunteers Trust has inaugurated a community water testing facility providing free TDS, fluoride, and bacteriological water tests for village panchayats and rural schools.",
            contentTa = "கிராம பஞ்சாயத்துகள் மற்றும் அரசுப் பள்ளிகளின் குடிநீர் மாதிரிகளை இலவசமாக பரிசோதிக்க புதிய சமுதாய நீர் பரிசோதனை மையம் தொடங்கப்பட்டுள்ளது.",
            category = "Project Update",
            dateStr = "Sep 18, 2026",
            isUrgent = false
        ),
        NewsEntity(
            titleEn = "Volunteer Recruitment Drive for Coastal Districts",
            titleTa = "கடலோர மாவட்டங்களுக்கான புதிய தன்னார்வலர்கள் சேர்க்கை",
            contentEn = "We are inviting enthusiastic youth, students and professionals from Nagapattinam and Mayiladuthurai to register as Disaster Response Volunteers. Free certification and emergency kit provided.",
            contentTa = "நாகப்பட்டினம் மற்றும் மயிலாடுதுறை மாவட்ட இளைஞர்கள் பேரிடர் மீட்பு தன்னார்வலர்களாக இணையலாம். இலவச சான்றிதழ் மற்றும் முதலுதவிப் பயிற்சி வழங்கப்படும்.",
            category = "Volunteer Call",
            dateStr = "Sep 15, 2026",
            isUrgent = false
        ),
        NewsEntity(
            titleEn = "50 Village Tanks Successfully Desilted Before Monsoon",
            titleTa = "மழைக்காலத்திற்கு முன் 50 கிராமக் குளங்கள் தூர்வாரப்பட்டன",
            contentEn = "Through community participation and volunteer mobilization, 50 historic village water tanks (ஊரணிகள்) have been revitalized across 5 Delta districts, securing irrigation and summer drinking water.",
            contentTa = "பொதுமக்கள் மற்றும் தன்னார்வலர்களின் கூட்டு முயற்சியால் 5 டெல்டா மாவட்டங்களில் 50 பழமையான கிராமக் குளங்கள் வெற்றிகரமாக தூர்வாரப்பட்டுள்ளன.",
            category = "Announcement",
            dateStr = "Sep 10, 2026",
            isUrgent = false
        )
    )

    const val TRUST_NAME = "Delta Volunteers Trust"
    const val TRUST_NAME_TA = "டெல்டா தன்னார்வலர்கள் அறக்கட்டளை"
    const val TAGLINE_EN = "People's Initiative for a Better Delta"
    const val TAGLINE_TA = "ஒப்புரவறிதல் – மக்கள் நலம், பேரிடர் மேலாண்மை & சுற்றுச்சூழல் இயக்கம்"
    const val MOTTO_TA = "ஒப்புரவறிதல்"
    const val MOTTO_EN = "Together We Serve The Delta"

    // Sacred Thirukkural Guidance
    const val THIRUKKURAL_LINE1 = "“கைம்மாறு வேண்டா கடப்பாடு மாரிமாட்டு"
    const val THIRUKKURAL_LINE2 = "என்ஆற்றுங் கொல்லோ உலகு”"
    const val THIRUKKURAL_REF = "அதிகாரம்-22 : ஒப்புரவறிதல் | குறள் : 211"
    const val THIRUKKURAL_MEANING_EN = "“What return can the world make unto the cloud which showers its blessings? The duty of noble souls to serve humanity seeks no reward.”"
    const val THIRUKKURAL_MEANING_TA = "மழை உலகிற்கு பெய்து உதவி செய்கிறது; அதற்கு பதிலாக உலகம் மழையிடம் என்ன கைம்மாறு செய்ய முடியும்? அதுபோலவே, உயர்ந்த சான்றோர் செய்யும் சமுதாய உதவிக்கும் கைம்மாறு தேவையில்லை."

    // Statutory Registrations
    const val REGD_NO = "19/2024 (Registered under Trust Act)"
    const val REGD_NO_SHORT = "19/2024"
    const val NGO_DARPAN_ID = "TN/2024/0484015"
    const val IT_EXEMPTION = "Registered U/s 12AA, 80G (5)"
    const val FSSAI_REG_NO = "22425439000002"

    // Partner Organizations
    const val PARTNER_TRUST_NAME = "PRIYAM TRUST"
    const val PARTNER_TRUST_REGD = "Regd. No. 72/2008"
    const val PARTNER_MOTTO_EN = "People | Environment | Development"
    const val PARTNER_MOTTO_TA = "மக்கள் நலம் • சுற்றுச்சூழல் பாதுகாப்பு • நிலையான வளர்ச்சி"
    const val PARTNER_NDSO_NAME = "NDSO (National Development & Social Organization)"

    const val JOINT_INITIATIVE_TITLE_EN = "JOINT INITIATIVE FOR A STRONGER, GREENER AND SAFER DELTA"
    const val JOINT_INITIATIVE_TITLE_TA = "வலிமையான, பசுமையான, பாதுகாப்பான டெல்டாவிற்கான கூட்டு முயற்சி"
    const val JOINT_INITIATIVE_SUBTITLE_EN = "Together for People, Nature and a Resilient Tomorrow"
    const val JOINT_INITIATIVE_SUBTITLE_TA = "மக்கள், இயற்கை மற்றும் நிலையான எதிர்காலத்திற்கான கூட்டுப் பயணம்"

    // Official Address & Contacts
    const val ADDRESS = "46, WEST STREET, VEDARANYAM-614810, NAGAPATTINAM DISTRICT, TAMILNADU"
    const val ADDRESS_TA = "46, மேற்கு தெரு, வேதாரண்யம் - 614810, நாகப்பட்டினம் மாவட்டம், தமிழ்நாடு"
    const val PHONE_PRIMARY = "+91 99435 15879"
    const val PHONE_PRIMARY_RAW = "9943515879"
    const val PHONE_SECONDARY = "+91 99408 60750"
    const val PHONE_SECONDARY_RAW = "9940860750"
    const val PHONE_EMERGENCY = "+91 99435 15879"
    const val EMAIL_TRUST = "cc.dvtrust@gmail.com"
    const val EMAIL_COORDINATOR = "exn.prabu@gmail.com"
    const val EMAIL = "cc.dvtrust@gmail.com"
    const val WHATSAPP_NUMBER = "919943515879"
    const val GOOGLE_MAPS_URL = "https://maps.google.com/?q=46+West+Street+Vedaranyam+Nagapattinam+Tamil+Nadu+614810"

    // Banking Details for Donations / CSR Contributions
    const val BANK_NAME = "CANARA BANK"
    const val BANK_BRANCH = "THETHAKUDI"
    const val BANK_ACCOUNT_TYPE = "CURRENT ACCOUNT"
    const val BANK_ACCOUNT_NO = "120033458392"
    const val BANK_IFSC_CODE = "CNRB0003620"
    const val BANK_MICR_CODE = "611015009"

    // Chief Leadership & Social Worker Profile
    const val LEADER_NAME = "G. PRABU, MSc, MSW"
    const val LEADER_ROLE = "SOCIAL WORKER"
    const val LEADER_TITLE_DELTA = "Chief Coordinator, DELTA VOLUNTEERS TRUST"
    const val LEADER_TITLE_PRIYAM = "Managing Director, PRIYAM TRUST"
    const val LEADER_CELL = "9943515879"
    const val LEADER_EMAIL = "exn.prabu@gmail.com"

    // Mission & Legacy Statement from Official Charter
    const val CHARTER_TEXT_EN = "Delta Volunteers Trust is a voluntary organization that works for the underprivileged people of the unique Cauvery Delta districts of Tamil Nadu, including Thanjavur, Thiruvarur, Nagapattinam, Mayiladuthurai Districts. Through this, the purpose of Delta Volunteers is to unite and serve the diverse people of the society, including NGOs operating in the Cauvery Delta region, social workers, service organizations, corporate social responsibility programs, and students.\n\nThe voluntary organizations affiliated with this organization have been providing various services to the people for the last 20 years. We are also undertaking various initiatives and development work focusing on the 17 Sustainable Development Goals of the United Nations. We are particularly focusing on protecting the well-being of children and eradicating poverty and hunger and coordinating preparedness work during disasters and in disaster and post-disaster development work."

    const val CHARTER_TEXT_TA = "டெல்டா தன்னார்வலர்கள் அறக்கட்டளை என்பது தமிழ்நாட்டின் தஞ்சாவூர், திருவாரூர், நாகப்பட்டினம், மயிலாடுதுறை உள்ளிட்ட தனித்துவம் வாய்ந்த காவேரி டெல்டா மாவட்டங்களின் அடித்தட்டு மக்களுக்காக இயங்கும் ஒரு தன்னார்வ அமைப்பாகும். இதன் மூலம் டெல்டா பகுதியில் இயங்கும் தொண்டு நிறுவனங்கள், சமூக சேவகர்கள், சேவை அமைப்புகள், கார்ப்பரேட் சமூகப் பொறுப்புணர்வு (CSR) திட்டங்கள் மற்றும் மாணவர்களை ஒன்றிணைத்து சமுதாயத்திற்கு சேவை செய்வதே நமது நோக்கமாகும்.\n\nஇந்த அமைப்போடு இணைந்த தன்னார்வ தொண்டு நிறுவனங்கள் கடந்த 20 ஆண்டுகளாக மக்களுக்கு பல்வேறு களப்பணிகளை ஆற்றி வருகின்றன. ஐக்கிய நாடுகள் சபையின் 17 நிலையான வளர்ச்சி இலக்குகளை (UN SDGs) அடிப்படையாகக் கொண்டு, குறிப்பாக குழந்தைகள் நலம் பேணுதல், வறுமை மற்றும் பசி ஒழிப்பு, பேரிடர் காலங்களில் தயார்நிலை பணிகள் மற்றும் பேரிடருக்குப் பிந்தைய மறுசீரமைப்பு பணிகளை தீவிரமாக மேற்கொண்டு வருகிறோம்."

    val HELPLINES = listOf(
        Pair("1077", "District Disaster Control Room (மாவட்ட பேரிடர் கட்டுப்பாட்டு அறை)"),
        Pair("1070", "State Emergency Operation Centre (மாநில அவசரகால மையம்)"),
        Pair("112", "National Emergency Unified Helpline (அனைத்து அவசர உதவி)"),
        Pair("108", "Free Ambulance Service (இலவச ஆம்புலன்ஸ் சேவை)"),
        Pair("101", "Fire & Rescue Services (தீயணைப்பு & மீட்புப் பணி)"),
        Pair("1098", "Childline Helpline (குழந்தைகள் பாதுகாப்பு உதவி)")
    )

    data class AssistanceTypeOption(
        val key: String,
        val titleEn: String,
        val titleTa: String,
        val iconEmoji: String
    )

    val BENEFICIARY_ASSISTANCE_OPTIONS = listOf(
        AssistanceTypeOption("Education", "Education", "கல்வி உதவி", "📚"),
        AssistanceTypeOption("Safe Drinking Water", "Safe Drinking Water", "பாதுகாப்பான குடிநீர்", "💧"),
        AssistanceTypeOption("Sanitation / Hygiene", "Sanitation / Hygiene", "சுகாதாரம் / கழிப்பறை வசதி", "🧼"),
        AssistanceTypeOption("Health / Medical Assistance", "Health / Medical Assistance", "மருத்துவ சிகிச்சை உதவி", "🏥"),
        AssistanceTypeOption("Food / Essential Needs", "Food / Essential Needs", "உணவு / அத்தியாவசியத் தேவைகள்", "🌾"),
        AssistanceTypeOption("Disaster Relief", "Disaster Relief", "பேரிடர் நிவாரணம் / புயல் சேதம்", "🌊"),
        AssistanceTypeOption("Livelihood Support", "Livelihood Support", "வாழ்வாதாரம் / சுயதொழில் ஆதரவு", "💼"),
        AssistanceTypeOption("Disability / Special Needs", "Disability / Special Needs", "மாற்றுத்திறனாளிகள் உதவி", "♿"),
        AssistanceTypeOption("Women & Child Support", "Women & Child Support", "பெண்கள் & குழந்தைகள் பாதுகாப்பு", "👩‍👧"),
        AssistanceTypeOption("Elderly Support", "Elderly Support", "முதியோர் ஆதரவு / உதவித்தொகை", "👴"),
        AssistanceTypeOption("Environmental / Community Support", "Environmental / Community Support", "சுற்றுச்சூழல் / சமுதாய உட்கட்டமைப்பு", "🌱"),
        AssistanceTypeOption("Other", "Other", "பிற உதவிகள்", "✨")
    )

    data class SupportingDocOption(
        val key: String,
        val labelEn: String,
        val labelTa: String
    )

    val SUPPORTING_DOC_OPTIONS = listOf(
        SupportingDocOption("Identity Proof", "Identity Proof (Aadhaar / Voter ID)", "அடையாளச் சான்று (ஆதார் / வாக்காளர் அட்டை)"),
        SupportingDocOption("Address Proof", "Address Proof (Ration Card / Smart Card)", "முகவரிச் சான்று (குடும்ப அட்டை / ஸ்மார்ட் கார்டு)"),
        SupportingDocOption("Income / BPL / Eligibility Proof", "Income / BPL / Eligibility Proof", "வருமானச் சான்று / வறுமைக்கோட்டு அட்டை"),
        SupportingDocOption("Medical Document", "Medical Document (Prescription / Hospital Estimate)", "மருத்துவ ஆவணம் (மருத்துவச் சீட்டு / மதிப்பீடு)"),
        SupportingDocOption("School / College Document", "School / College Document (Fee Structure / ID)", "பள்ளி / கல்லூரி ஆவணம் (கட்டண ரசீது / அடையாள அட்டை)"),
        SupportingDocOption("Photograph", "Photograph (Applicant Photo)", "விண்ணப்பதாரர் சமீபத்திய புகைப்படம்"),
        SupportingDocOption("Other", "Other Supporting Document", "பிற சான்றாவணங்கள்")
    )

    val INITIAL_BENEFICIARY_REQUESTS = listOf(
        BeneficiaryRequestEntity(
            formNo = "DVT-BEN-2026-1048",
            formDate = "20/09/2026",
            fullName = "M. Selvamani",
            guardianName = "Late Murugesan",
            ageOrDob = "38 Years",
            gender = "Female",
            mobileNumber = "9842512340",
            alternateContact = "9443210987",
            idProofNumber = "XXXX-XXXX-4589",
            address = "12/A, North Fishermen Colony, Kodiyakarai",
            villageTown = "Kodiyakarai (Point Calimere)",
            panchayat = "Kodiyakarai",
            taluk = "Vedaranyam",
            district = "Nagapattinam (நாகப்பட்டினம்)",
            pinCode = "614807",
            maritalStatus = "Widowed",
            familyMembersCount = "3",
            childrenCount = "2",
            occupation = "Fish Net Weaver / Daily Wage",
            monthlyIncome = "4,500",
            housingStatus = "Temporary",
            hasGovtAssistance = false,
            govtAssistanceDetails = "",
            assistanceTypes = "Education, Safe Drinking Water",
            otherAssistanceType = "",
            requestProblemDetails = "Daughter studying in 10th standard; struggling to pay school exam and notebook fees. Also high salinity in drinking tap water in our coastal hamlet requires community RO filter can support.",
            urgency = "Urgent",
            estimatedAssistanceRequired = "8,500",
            applicantContribution = "1,500",
            assistanceRequestedFromTrust = "7,000",
            supportingDocuments = "Identity Proof, Address Proof, School / College Document",
            otherDocumentDetails = "",
            isDeclarationAgreed = true,
            applicantSignatureName = "M. Selvamani",
            declarationDate = "20/09/2026",
            declarationPlace = "Vedaranyam",
            beneficiaryId = "BEN-2609-102",
            fieldVerificationRequired = true,
            verificationOfficer = "K. Ramanathan (Field Coordinator)",
            verificationDate = "22/09/2026",
            recommendation = "Eligible",
            assistanceApproved = "7,000",
            modeOfAssistance = "Direct Service",
            projectName = "Delta Vidya Jyothi & Coastal Pure Water Project",
            verifiedBy = "G. Prabu (Chief Coordinator)",
            approvedBy = "Trustee Board, Delta Volunteers Trust",
            status = "Approved"
        ),
        BeneficiaryRequestEntity(
            formNo = "DVT-BEN-2026-1052",
            formDate = "23/09/2026",
            fullName = "K. Arumugam",
            guardianName = "Kandasamy",
            ageOrDob = "52 Years",
            gender = "Male",
            mobileNumber = "9789123456",
            alternateContact = "",
            idProofNumber = "XXXX-XXXX-7812",
            address = "45, East Street, Thalainayar",
            villageTown = "Thalainayar",
            panchayat = "Thalainayar South",
            taluk = "Vedaranyam",
            district = "Nagapattinam (நாகப்பட்டினம்)",
            pinCode = "614712",
            maritalStatus = "Married",
            familyMembersCount = "4",
            childrenCount = "2",
            occupation = "Small Tenant Farmer",
            monthlyIncome = "6,000",
            housingStatus = "Rental",
            hasGovtAssistance = false,
            govtAssistanceDetails = "",
            assistanceTypes = "Health / Medical Assistance",
            otherAssistanceType = "",
            requestProblemDetails = "Underwent emergency cataract surgery at Nagapattinam GH; requires post-operative medicine kit and transport assistance.",
            urgency = "Normal",
            estimatedAssistanceRequired = "5,000",
            applicantContribution = "1,000",
            assistanceRequestedFromTrust = "4,000",
            supportingDocuments = "Identity Proof, Medical Document",
            otherDocumentDetails = "",
            isDeclarationAgreed = true,
            applicantSignatureName = "K. Arumugam",
            declarationDate = "23/09/2026",
            declarationPlace = "Thalainayar",
            beneficiaryId = "BEN-2609-105",
            fieldVerificationRequired = true,
            verificationOfficer = "S. Vignesh",
            verificationDate = "24/09/2026",
            recommendation = "Under Review",
            assistanceApproved = "",
            modeOfAssistance = "",
            projectName = "Delta Arogya Medical Aid Scheme",
            verifiedBy = "S. Vignesh",
            approvedBy = "",
            status = "Under Verification"
        )
    )
}
