class CreateReports < ActiveRecord::Migration
  def change
    create_table :reports do |t|
      t.string :title
      t.string :lat
      t.string :lng
      t.string :description

      t.timestamps
    end
  end
end
