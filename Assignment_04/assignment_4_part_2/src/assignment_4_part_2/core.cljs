(ns assignment_4_part_2.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(def base-state-stack (list '{:mode "none"}))

(defonce app-db (reagent/atom {:state-stack base-state-stack ; stack of state maps defining changes in mode or drawn figures
                               :mouse-clicks [] ; vector of maps storing saved mouse clicks within the draw area
                               :cursor {:x 0 :y 0}})) ; map storing the last saved cursor position within the draw area

;------------------------------------------------------------------------------

(defn drawing?
  "Returns true if a figure is currently being drawn, false otherwise"
  []
  (> (count (:mouse-clicks @app-db)) 0))
  
(defn can-draw?
  []
  "Returns true if figures can be drawn in the current mode, false otherwise"
  (not (= (current-mode) "none")))

(defn can-undo?
  "Returns true if an undo step can be performed, false otherwise"
  []
  (and (> (count (:state-stack @app-db)) 1) (not (drawing?))))

(defn current-mode
  "Returns the mode of the current state"
  []
  ((first (:state-stack @app-db)) :mode))

(defn clear-mouse-clicks
  "Removes all elements from the mouse-clicks vector"
  []
  (swap! app-db assoc-in [:mouse-clicks] []))
  
(defn push-mode
  "Inserts a new state onto the top of the state-stack with mode m. The new state will act as the current state"
  [m]
  (swap! app-db assoc-in [:state-stack] (conj (:state-stack @app-db) {:mode m})))

(defn push-figure
  "Inserts a new state onto the top of the state-stack with figure-points fp. The new state will act as the current state"
  [fp]
  (swap! app-db assoc-in [:state-stack] (conj (:state-stack @app-db) {:mode (current-mode) :figure-points fp})))

(defn undo
  "Undo the drawing of one figure or one mode selection"
  []
  (swap! app-db assoc-in [:state-stack] (rest (:state-stack @app-db)))
  (clear-mouse-clicks))

(defn clear
  "Removes all elements from the state-stack"
  []
  (swap! app-db assoc-in [:state-stack] base-state-stack)
  (clear-mouse-clicks)) ;;;;;;;;;;;;;;;;;;; should not have to clear-clicks here
    
(defn save-click
  "Inserts the mouse click coordinates [mx, my] into the mouse-clicks list as a map"
  [mx my]
  (swap! app-db assoc-in [:mouse-clicks] (conj (:mouse-clicks @app-db) {:x mx :y my})))

(defn process-click
  "Processes the mouse click coordinates [mx, my] against the current state"
  [mx my]
  (when (can-draw?)
	(save-click mx my))
  (when (= (count (:mouse-clicks @app-db)) 2)
    (push-figure (:mouse-clicks @app-db))
    (clear-mouse-clicks)))

;------------------------------------------------------------------------------	

(defn construct-line
  "Returns a line shape given a vector of two XY point maps"
  [[{p1x :x, p1y :y} {p2x :x, p2y :y}]]
  [:line {:x1 p1x :y1 p1y :x2 p2x :y2 p2y :stroke "blue"}])

(defn point-distance
  "Returns the calculated distance between two points"
  [[{p1x :x, p1y :y} {p2x :x, p2y :y}]]
  (let [deltax (- p1x p2x)
        deltay (- p1y p2y)]
    (Math/sqrt (+ (* deltax deltax)(* deltay deltay)))))

(defn construct-circle
  "Returns a circle shape given a vector of two XY point maps"
  [[{p1x :x, p1y :y} _ :as points]]
  [:circle {:r (point-distance points) :cx p1x :cy p1y :stroke "red" :fill :none}])

(defn construct-rectangle
  "Returns a rectangle shape given a vector of two XY point maps"
  [[{p1x :x, p1y :y} {p2x :x, p2y :y}]]
  (let [rectW (Math/abs (- p1x p2x)) rectH (Math/abs (- p1y p2y))]
    (cond
      (and (< p1x p2x) (< p1y p2y))
        [:rect {:x p1x :y p1y :width rectW :height rectH :stroke "green" :fill :none}]
  	  (and (> p1x p2x) (> p1y p2y))
        [:rect {:x p2x :y p2y :width rectW :height rectH :stroke "green" :fill :none}]
	  (> p1x p2x)
  	    [:rect {:x p2x :y p1y :width rectW :height rectH :stroke "green" :fill :none}]
	  (> p1y p2y)
  	    [:rect {:x p1x :y p2y :width rectW :height rectH :stroke "green" :fill :none}])))

(defn figure-preview
  "Returns a shape based on the current mode, last mouse-click position, and the last saved position of the mouse cursor"
  []
  (when (= (count (:mouse-clicks @app-db)) 1)
    {:mode (current-mode) :figure-points [(first (:mouse-clicks @app-db)) (:cursor @app-db)]}))

(defn draw-graphics
  "Returns sequence of all drawable shapes"
  []
  ; conjoin a collection of (all figure states + figure preview (seen when drawing))
  ; reverse collection in order to draw figures in the same order in which they were origionally created
  (let [fig-states (reverse (filter #( and (not-empty %) (not-empty (% :figure-points))) (conj (:state-stack @app-db) (figure-preview))))]
    (for [state fig-states :let [points (state :figure-points)]]
	  (case (state :mode)
        "line" (construct-line points)
        "circle" (construct-circle points)
        "rectangle" (construct-rectangle points)))))

;------------------------------------------------------------------------------

(defn main []
  [:div#main {:style {:display "inline-block"}}
    (into
      [:svg#draw-area {:width 600 :height 600 :style {:position :fixed :top 0 :left 0 :border "1px solid purple"}
                       :on-mouse-move #(swap! app-db assoc-in [:cursor] {:x (.-clientX %) :y (.-clientY %)})
  				       :on-click #(process-click (.-clientX %) (.-clientY %))}]
	  (draw-graphics))
  
    [:div#palette {:style {:margin-left 650 :display "inline-block"}}
      [:select {:on-change #(push-mode (-> % .-target .-value)) :title "Select Draw Mode" :value (current-mode) :disabled (drawing?)}
        [:option {:value "none"} "None"]
        [:option {:value "line"} "Line"]
        [:option {:value "circle"} "Circle"]
        [:option {:value "rectangle"} "Rectangle"]]
      [:button {:on-click #(undo) :style {:margin-left 25} :disabled (not (can-undo?))} "Undo"]
      [:button {:on-click #(clear) :style {:margin-left 25} :disabled (not (can-undo?))} "Clear"]]
  
    [:div#info {:style {:margin-left 650 :margin-top 100}}
      [:h1 "Assignment 4 - Part 2"]
      [:h4 "Drawing = " (str (drawing?))]
      [:h4 "Cursor = " (str (:cursor @app-db))]
      [:h4 "States = " (count (:state-stack @app-db))]
      [:h4 "Clicks = " (count (:mouse-clicks @app-db))]
      [:h4 "Mode = " (current-mode)]]])

(reagent/render-component [main]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)








