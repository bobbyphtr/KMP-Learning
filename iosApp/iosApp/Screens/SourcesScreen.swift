//
//  SourcesScreen.swift
//  iosApp
//
//  Created by Bobby   Pehtrus on 26/06/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import shared
import SwiftUI


struct SourcesScreen: View {
    
    @Environment(\.dismiss) var dismiss
    @ObservedObject private(set) var viewModel: SourcesViewModelWrapper
    
    
    var body: some View {
        NavigationStack {
            VStack {
                if viewModel.state.isLoading {
                    ProgressView()
                }
                
                if !viewModel.state.sources.isEmpty {
                    ScrollView {
                        LazyVStack {
                            ForEach(viewModel.state.sources, id: \.self) { source in
                                createRow(source: source)
                            }
                        }
                    }
                }
            }
            .navigationTitle("Sources")
            .toolbar {
                ToolbarItem {
                    Button {
                        self.dismiss()
                    } label: {
                        Text("Done")
                    }

                }
            }
            .padding(.horizontal, 16.0)
           
        }
        .onAppear {
            viewModel.startObserving()
        }
        .refreshable {
            viewModel.viewModel.getSources(forceFetch: true)
        }
    }
    
    @ViewBuilder
    private func createRow(source: Source) -> some View {
        VStack(alignment: .leading) {
            Text(source.sourceName)
                .font(.title)
            Text(source.sourceDescription)
                .font(.body)
            HStack {
                Spacer()
                Text("\(source.sourceCountry) - \(source.sourceLanguage)")
                    .font(.footnote)
            }
        }
    }
    
}

extension SourcesScreen {
    
    
    @MainActor
    class SourcesViewModelWrapper: ObservableObject {

        var viewModel: SourcesViewModel
        
        @Published var state: SourcesState
        
        init() {
            self.viewModel = SourcesInjector().sourcesViewModel
            state = viewModel.sourceState.value
        }
        
        func startObserving() {
            Task {
                for await sourceState in viewModel.sourceState {
                    self.state = sourceState
                }
            }
        }
        
    }
    
}
