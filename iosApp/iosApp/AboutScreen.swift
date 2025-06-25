//
//  AboutScreen.swift
//  iosApp
//
//  Created by Bobby   Pehtrus on 25/06/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct AboutScreen: View {
    var body: some View {
      NavigationView {
        AboutListView()
          .navigationTitle("About Device")
      }
    }
}

#Preview {
    AboutScreen()
}
