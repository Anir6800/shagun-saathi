# Screens

The screen map for design and build. A Stitch design step adds detail to a row (Stitch id, design status → `designed`, components, animations). A build step marks a screen `built`.

| # | Screen | Platform | Group | Stitch id | Design status | Build status | Primary action | Components | Animations | Notes |
|---|---|---|---|---|---|---|---|---|---|---|
| 1 | Splash | Android | Onboarding | — | planned | todo | Auto-advance to Onboarding/Home | Logo mark | Fade/scale in | |
| 2 | Onboarding | Android | Onboarding | — | planned | todo | Continue to Sign in | Paged intro cards | Page transition | |
| 3 | Sign in | Android | Onboarding | — | planned | todo | Sign in (Google or email) | Buttons, text fields | Press feedback | D-004: Google + email only, no OTP. |
| 4 | Create account and reset password | Android | Onboarding | — | planned | todo | Create account / send reset email | Form fields | Press feedback | |
| 5 | Profile setup | Android | Onboarding | — | planned | todo | Save profile, continue to Home | Form fields, avatar | Enter stagger | |
| 6 | Home | Android | Event setup | — | planned | todo | Create event / open an existing event | Event list/cards, floating pill bottom nav | Enter stagger | |
| 7 | Create event | Android | Event setup | — | planned | todo | Create the event | Form fields | Press feedback | |
| 8 | Event dashboard | Android | Event setup | — | planned | todo | Navigate into event features | Summary cards, nav tiles | Count-up on totals | Helpers must not see totals here (RULES.md). |
| 9 | Invite writers | Android | Event setup | — | planned | todo | Generate invite link/QR | QR code, share sheet | Press feedback | |
| 10 | Join by scan | Android | Event setup | — | planned | todo | Scan QR to join event | Camera scanner | Scan feedback | |
| 11 | Add entry | Android | Entry recording | — | planned | todo | Save a guest entry | Guest search, type selector | Enter stagger | Ten-seconds-per-guest, one-hand target screen. |
| 12 | Cash note counter | Android | Entry recording | — | planned | todo | Confirm notes-in total | Denomination counter | Count-up | Feeds `notesIn` field. |
| 13 | Change given | Android | Entry recording | — | planned | todo | Confirm change-out total | Denomination counter | Count-up | Feeds `changeOut` field. |
| 14 | Gift entry | Android | Entry recording | — | planned | todo | Record a non-cash gift | Text field, photo attach | Press feedback | |
| 15 | UPI QR | Android | Entry recording | — | planned | todo | Show/generate UPI QR | QR display, UPI ID field, image upload | Fade in | D-009: app never processes payment. |
| 16 | Entries list | Android | Entry recording | — | planned | todo | Open an entry to edit/void | List with search/filter | List enter stagger | |
| 17 | Edit or void | Android | Entry recording | — | planned | todo | Save edit / void with reason | Form fields, reason field | Press feedback | D-010: never hard-delete. |
| 18 | Cash box stock | Android | Cash box | — | planned | todo | View current box contents | Denomination breakdown | Count-up | |
| 19 | Break and successor | Android | Cash box | — | planned | todo | Hand box to a successor writer mid-event | Writer picker | Press feedback | |
| 20 | Handover count | Android | Cash box | — | planned | todo | Recount and confirm box total | Denomination counter | Count-up | |
| 21 | Handover summary | Android | Cash box | — | planned | todo | Confirm handover complete | Summary card, signatures/confirm | Fade in | |
| 22 | End event review | Android | Wrap-up | — | planned | todo | Confirm event totals before closing | Summary cards | Count-up | |
| 23 | PDF preview | Android | Wrap-up | — | planned | todo | Generate/share the PDF ledger | PDF viewer, share sheet | Fade in | |
| 24 | Guests list | Android | Guests and AI | — | planned | todo | Open a guest's history | List with search | List enter stagger | |
| 25 | Guest history | Android | Guests and AI | — | planned | todo | Review a guest's gifts across events | Timeline/list | List enter stagger | |
| 26 | AI chat | Android | Guests and AI | — | planned | todo | Ask a question about past gifts | Chat input/thread | Message enter | D-011: AI answers via tool calling, code does arithmetic. |
| 27 | Give-back advice | Android | Guests and AI | — | planned | todo | View suggested give-back amount | Advice card | Fade in | AI explains, does not compute — code computes (D-011). |
| 28 | Notebook capture | Android | Admin | — | planned | todo | Photograph a paper notebook page | Camera capture | Capture feedback | |
| 29 | Notebook review | Android | Admin | — | planned | todo | Confirm/correct captured entries | Editable list | List enter stagger | |
| 30 | Writers and roles | Android | Admin | — | planned | todo | Change a member's role | Member list, role picker | Press feedback | |
| 31 | Settings | Android | Admin | — | planned | todo | Change language/app preferences | Settings list | Press feedback | Reduce-motion toggle lives here. |
| 32 | App lock PIN | Android | Admin | — | planned | todo | Set/enter app lock PIN | PIN pad | Press feedback | |
| 33 | Landing | Web | Marketing | — | planned | todo | Learn about the app, go to Download | Hero, feature sections | Scroll reveal | |
| 34 | Download APK | Web | Marketing | — | planned | todo | Download the signed APK | Download button, version info | Press feedback | D-006: APK via GitHub Releases. |
| 35 | Login | Web | Account | — | planned | todo | Sign in to the web dashboard | Form fields | Press feedback | |
| 36 | Dashboard | Web | Account | — | planned | todo | Navigate to events/ledgers | Event list | Enter stagger | |
| 37 | Event ledger | Web | Account | — | planned | todo | View an event's ledger | Table/list | List enter stagger | |
| 38 | Guest history | Web | Account | — | planned | todo | Review a guest's gift history | Timeline/list | List enter stagger | |
| 39 | AI chat | Web | Account | — | planned | todo | Ask a question about past gifts | Chat input/thread | Message enter | Same tool-calling rule as Android (D-011). |
| 40 | PDF and export | Web | Account | — | planned | todo | Download/export the PDF ledger | PDF viewer, export button | Fade in | |
| 41 | Settings | Web | Account | — | planned | todo | Change account preferences | Settings list | Press feedback | |
| 42 | Privacy and help | Web | Marketing | — | planned | todo | Read privacy policy / get help | Static content | Scroll reveal | |

Design status values: `planned`, `designed`. Build status values: `todo`, `built`.
